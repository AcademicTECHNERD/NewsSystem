package com.itheima.validation;

import com.itheima.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
// 定义一个自定义的约束验证器，用于验证字符串值
//ConstraintValidator（自定义的注解，要验证的字段类型）
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * 校验方法
     *
     * @param value   将来要校验的数据（类型为String）
     * @param context 校验的上下文信息
     *
     * @return 如果返回false，则校验不通过；如果返回true，则校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 校验规则：如果值为null，返回false
        if (value == null) {
            return false; // 代表校验不通过
        }
        // 如果值为“已发布”或“草稿”，返回true
        if (value.equals("已发布") || value.equals("草稿")) {
            return true; // 代表校验通过
        }
        // 对于其他字符串值，返回false
        return false; // 代表校验不通过
    }
}

