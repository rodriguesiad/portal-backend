package portal.editais.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataFimMaiorQueInicioValidator.class)
public @interface DataFimMaiorQueInicio {

    String message() default "A data de fim deve ser igual ou posterior à data de início.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}