package com.stylefeng.guns.core.base.tips;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
@Slf4j
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        if (code ==700){
            this.status = code;
            this.msg = message;
        }else {
            this.status = 999;
            this.msg = "请联系管理员";
        }

    }
}
