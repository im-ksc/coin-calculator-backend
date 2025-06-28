package nus.iss.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import nus.iss.validator.CoinDenominationConstraint;

@Data
@NoArgsConstructor
public class CoinCalculatorRequest {

    @NotNull
    @DecimalMin(
            value = "0.00",
            inclusive = true,
            message = "must be between 0.00 to 10000.00")
    @DecimalMax(
            value = "10000.00",
            inclusive = true,
            message = "must be between 0.00 to 10000.00")
    private Double target;

    @NotNull
    @CoinDenominationConstraint
    private List<Double> coinDenominations;
}
