package com.book.addressbook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AllowedValuesValidator.class})
public @interface AllowedValues {

    String message() default "Field value should be from list of ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] values();
}