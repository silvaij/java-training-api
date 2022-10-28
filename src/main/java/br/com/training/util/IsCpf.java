package br.com.training.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Repeatable(IsCpf.List.class)
@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsCpfValidation.class)
public @interface IsCpf {

    String message() default "{CPF INVALIDO}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface List {
        IsCpf[] value();
    }
}
