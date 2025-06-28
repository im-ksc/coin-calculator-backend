package nus.iss.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CoinDenominationValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CoinDenominationConstraint  {
    String message() default "Coin Denominations must be between [ 0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000 ]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
