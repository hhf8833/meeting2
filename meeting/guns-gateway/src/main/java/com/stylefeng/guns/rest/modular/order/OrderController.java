package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stylefeng.guns.api.alipay.AliPayServicerAPI;
import com.stylefeng.guns.api.alipay.vo.AliPayInfoVO;
import com.stylefeng.guns.api.alipay.vo.AliPayResultVO;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.api.order.vo.OrderVO;
import com.stylefeng.guns.core.util.TokenBucket;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order/")
public class OrderController {
    private static TokenBucket tokenBucket = new TokenBucket();
    String img_pre = "http://img.meetingshop.cn/";

    @Reference(interfaceClass = OrderServiceAPI.class,check = false,group = "order2018",filter = "tracing")
    private OrderServiceAPI orderServiceAPI;

    @Reference(interfaceClass = OrderServiceAPI.class,check = false,group = "order2017")
    private OrderServiceAPI orderServiceAPI2017;

    @Reference(interfaceClass = AliPayServicerAPI.class,check = false,filter = "tracing")
    private AliPayServicerAPI aliPayServicerAPI;

    //降级熔断   hystrix自带的信号量隔离、线程池隔离、线程切换导致 threadLocal中的token获取不到

    public ResponseVo error(@RequestParam("fieldId") Integer fieldId,@RequestParam("soldSeats")String soldSeats, String seatsName){
        return ResponseVo.serviceFail("抱歉，下单人太多，请稍后重试");
    }

    /**
     * post , 需携带JWT信息
     * fieldId	        场次编号	是
     * soldSeats	购买座位编号	是
     * seatsName	购买座位的名称	是
     * @return
     */
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
        @HystrixProperty(name="execution.isolation.strategy", value = "THREAD"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value
                = "500"),
        //设置在一个滚动窗口中，打开断路器的最少请求数。
        // 当在配置时间窗口内请求数量至少达到此数量并且错误率达到errorThresholdPercentage时，熔断;
        // 比如：如果值是20，在一个窗口内（比如10秒），收到19个请求，即使这19个请求都失败了，断路器也不会打开。当够20且其中错误率占50%的时候熔断
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
       //休眠时间窗口期（休眠多久进入半开模式（单位毫秒，默认5秒））
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")},
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                //设置统计的滚动窗口的时间段大小。该属性是线程池保持指标时间长短。
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
        })
    @PostMapping("buyTickets")
    public ResponseVo buyTickets(@RequestParam("fieldId") Integer fieldId,@RequestParam("soldSeats")String soldSeats, String seatsName){
        if (tokenBucket.getToken()){
            /**
             * 这里如果采用分布式事务的话需要再写一个consumer类把isTrueSeats、isNotSoldSeats、saveOrderInfo全部写进去，看最终是否能返回的cancel还是
             * confirm来决定控制层是如何返回值
             *
             */


            //验证售出的票是否为真，即是否有这个座位
            boolean isTrue = orderServiceAPI.isTrueSeats(fieldId + "", soldSeats);

            //已经销售的座位里，有没有这个座位
            boolean notSoldSeats = orderServiceAPI.isNotSoldSeats(fieldId + "", soldSeats);
            if(!isTrue || !notSoldSeats){
                return ResponseVo.serviceFail("座位异常，请重试");
            }
            //创建订单信息,注意要获取登录的人
            String userId = CurrentUser.getCurrentUser();
            if (userId ==null ||userId.trim().length() ==0){
                return ResponseVo.serviceFail("用户未登录");
            }
            OrderVO orderVO = orderServiceAPI.saveOrderInfo(fieldId, soldSeats, seatsName, Integer.parseInt(userId));
            if (orderVO ==null){
                return ResponseVo.serviceFail("购票异常");
            }else {
                return ResponseVo.success(orderVO);
            }
        }else {
            return ResponseVo.serviceFail("当前排队人较多，请稍后重试");
        }
    }

    /**
     * nowPage	当前页	否，默认第1页
     * pageSize	每页多少条	否，默认5条

     * @return
     */
    @PostMapping("getOrderInfo")
    public ResponseVo getOrderInfo(@RequestParam(name = "nowPage",defaultValue = "1",required = false) Integer nowPage,
                                   @RequestParam(name = "nowPage",defaultValue = "5",required = false)Integer pageSize){

        //获取当前登录人的信息
        String userId = CurrentUser.getCurrentUser();
        if (userId ==null ||userId.trim().length() ==0){
            return ResponseVo.serviceFail("用户未登录");
        }
        //使用当前登陆人已经购买的订单
        Page<OrderVO> page = new Page<>(nowPage,pageSize);
        //2018
        Page<OrderVO> orders = orderServiceAPI.getOrderByUserId(Integer.parseInt(userId), page);
//        2017
        Page<OrderVO> orders2017 = orderServiceAPI2017.getOrderByUserId(Integer.parseInt(userId), page);

        int totailPage = (int) (orders2017.getPages()+orders.getPages());
        List<OrderVO> orderVOList = new ArrayList<>();
        orderVOList.addAll(orders.getRecords());
        orderVOList.addAll(orders2017.getRecords());

        return ResponseVo.success(nowPage,totailPage,"",orderVOList);

    }

    @PostMapping("getPayInfo")
    public ResponseVo getPayInfo(String orderId){
        //获取当前登录人的信息
        String userId = CurrentUser.getCurrentUser();
        if (userId ==null ||userId.trim().length() ==0){
            return ResponseVo.serviceFail("用户未登录");
        }
        //订单二维码结果
        AliPayInfoVO aliPayInfoVO = aliPayServicerAPI.getQRCode(orderId);
        if (aliPayInfoVO ==null){
            return ResponseVo.serviceFail("二维码获取失败请重试");
        }
        return ResponseVo.success(img_pre,aliPayInfoVO);
    }
    //注： orderStatus状态为1为支付成功，其余均为支付失败
    @PostMapping("getPayResult")
    public ResponseVo getPayResult(
            @RequestParam("orderId") String orderId,
            @RequestParam(name="tryNums",required = false,defaultValue = "1") Integer tryNums){
        // 获取当前登陆人的信息
        String userId = CurrentUser.getCurrentUser();
        if(userId==null || userId.trim().length()==0){
            return ResponseVo.serviceFail("抱歉，用户未登陆");
        }

        // 将当前登陆人的信息传递给后端，隐式传递
        RpcContext.getContext().setAttachment("userId",userId);

        // 判断是否支付超时
        if(tryNums>=4){
            return ResponseVo.serviceFail("订单支付失败，请稍后重试");
        }else{
            AliPayResultVO aliPayResultVO = aliPayServicerAPI.getOrderStatus(orderId);
            if(aliPayResultVO == null || ToolUtil.isEmpty(aliPayResultVO.getOrderId())){
                AliPayResultVO serviceFailVO = new AliPayResultVO();
                serviceFailVO.setOrderId(orderId);
                serviceFailVO.setOrderStatus(0);
                serviceFailVO.setOrderMsg("支付不成功");
                return ResponseVo.success(serviceFailVO);
            }
            return ResponseVo.success(aliPayResultVO);
        }
    }
}
