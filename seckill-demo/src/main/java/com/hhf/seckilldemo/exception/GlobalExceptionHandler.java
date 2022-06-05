package com.hhf.seckilldemo.exception;

import com.hhf.seckilldemo.vo.RespBean;
import com.hhf.seckilldemo.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。
 *  @ControllerAdvice 和 @RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。
 * RestControllerAdvice = ControllerAdvice + ResponseBody
 *
 * @author HP
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e){
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            return RespBean.error(globalException.getRespBeanEnum());
        }else if (e instanceof BindException){
            BindException bindException = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常："+bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
