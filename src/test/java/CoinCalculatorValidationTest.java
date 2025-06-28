import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import nus.iss.api.CoinCalculatorRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CoinCalculatorValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validRequest() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(50.0);
        request.setCoinDenominations(List.of(0.1, 0.5, 1.0));

        Set<ConstraintViolation<CoinCalculatorRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidTargetOver10000() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(10001.0);
        request.setCoinDenominations(List.of(0.1, 0.5));

        Set<ConstraintViolation<CoinCalculatorRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidTargetBelow0() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(-1.0);
        request.setCoinDenominations(List.of(0.1, 0.5));

        Set<ConstraintViolation<CoinCalculatorRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidDenomination() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(100.0);
        request.setCoinDenominations(List.of(0.03));

        Set<ConstraintViolation<CoinCalculatorRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
}
