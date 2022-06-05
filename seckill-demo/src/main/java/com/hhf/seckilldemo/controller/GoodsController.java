package com.hhf.seckilldemo.controller;

import com.hhf.seckilldemo.pojo.GoodVo;
import com.hhf.seckilldemo.pojo.User;
import com.hhf.seckilldemo.service.IGoodsService;
import com.hhf.seckilldemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/goods")
public class GoodsController {

/*    @Autowired
    private IUserService userService;*/
    @Autowired
    private RedisTemplate redisTemplate ;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    /*@RequestMapping("toList")
    //public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket){
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket){
        if (!StringUtils.hasLength(ticket)){
            return "login";
        }
        //之前是通过session的ticket找到user的，现在不用这样了用redis找到
//        User user = (User) session.getAttribute(ticket);
        User user = userService.getUserByCookie(ticket, request, response);
        if (user ==null){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }*/

    /**
     * 使用拦截器对用户进行一定的判断
     * @param model
     * @param 
     * @return
     */
    @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList( Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response){
//        if (!StringUtils.hasLength(ticket)){
//            return "login";
//        }
//        //之前是通过session的ticket找到user的，现在不用这样了用redis找到
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        if (user ==null){
//            return "login";
//        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (StringUtils.hasLength(html)){
            System.out.println("从redis读入list页面");
            return html;
        }
        User user = (User) request.getAttribute("user");
        System.out.println("toList"+user);
        //model.addAttribute("user",user);
        session.setAttribute("user",user);
        List<GoodVo> goodsVo = goodsService.findGoodsVo();
        model.addAttribute("goodsList",goodsVo);
        // return "goodsList";
        //手动渲染然后放入redis
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", webContext);
        if (StringUtils.hasLength(html)){
            valueOperations.set("goodsList",html,60, TimeUnit.SECONDS);
        }
        return html;
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(@PathVariable("goodsId")Long goodsId, Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        GoodVo goodsVoByGoodsId = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVoByGoodsId.getStartDate();
        Date endDate = goodsVoByGoodsId.getEndDate();
        Date nowDate = new Date();
        int seckillStatus =0;
        int remainSeconds =0;
        if (nowDate.before(startDate)){
            remainSeconds=(int)(startDate.getTime()-nowDate.getTime()) /1000;
        }else if (nowDate.after(endDate)){
            seckillStatus =2;
            remainSeconds =-1;
        }else {
            seckillStatus =1;
            remainSeconds=0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("goods",goodsVoByGoodsId);
        return "goodsDetail";
    }
}
