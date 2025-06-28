package nus.iss.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class CoinDenominationValidator
        implements ConstraintValidator<CoinDenominationConstraint, List<Double>> {
    @Override
    public boolean isValid(List<Double> values, ConstraintValidatorContext context) {

        double[] allowedDenominations =
                new double[] {0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0};

        return values.stream()
                .allMatch(
                        v -> {
                            for (double d : allowedDenominations) {
                                if (Double.compare(v, d) == 0) return true;
                            }
                            return false;
                        });
    }
}
