package com.hhf.seckilldemo.vo;

import com.hhf.seckilldemo.utils.ValidatorUtil;
import com.hhf.seckilldemo.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMoblieValidator implements ConstraintValidator<IsMobile,String> {
    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMobile(s);

        }else {
            if (!StringUtils.hasLength(s)){
                return true;
            }else {
                return ValidatorUtil.isMobile(s);
            }
        }

    }
}
