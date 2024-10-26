package com.itheima.anno;

import com.itheima.validation.StateValidation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义了一个自定义的注解 @State，主要用于 Java Bean 的验证，通常配合 Hibernate Validator 使用
 *这个自定义注解 @State 主要用于对字段进行状态验证，确保其值只能是“已发布”或“草稿”。
 * 通过与验证逻辑结合使用，可以在数据传输对象（DTO）或实体类中添加业务逻辑校验，提高代码的可维护性和可靠性。
 */
@Documented       //元注解，表示这个注解将包含在 JavaDoc 中
@Target({ FIELD})//元注解，指定这个注解可以应用于字段（FIELD），也就是说可以用在类的属性上
@Retention(RUNTIME)//元注解，指定注解在运行时仍然可用，这样在运行时可以通过反射获取这个注解的信息。
//约束注解，指定了用来验证的逻辑的实现类 StateValidation。
@Constraint(validatedBy = { StateValidation.class})//指定提供校验规则的类

/**
 * @interface 是 Java 中用于定义注解的关键字。
 * @interface 看起来像接口的定义，但它实际上是一个特殊的类型，用于创建自定义注解
 */
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或者草稿";
    //指定分组
    Class<?>[] groups() default { };
    //负载  获取到State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
