package com.example.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@ConstraintComposition(CompositionType.OR)
@Target({FIELD})
@Retention(RUNTIME)
@CNPJ
@CPF
public @interface Documento {

    String message() default "deu ruim ai meu broder";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
