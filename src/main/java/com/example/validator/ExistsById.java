package com.example.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistsByIdValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistsById {

    String message() default "{com.deveficiente.beanvalidation.existsbyid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String fieldName();
    Class<?> domainClass();

}
