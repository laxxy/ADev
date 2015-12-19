package com.audev.common.Entity.Annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by cosxt on 19.12.2015.
 */
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "This email is already used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
