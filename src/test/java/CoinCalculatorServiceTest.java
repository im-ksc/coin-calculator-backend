import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import nus.iss.service.CoinCalculatorService;
import org.junit.jupiter.api.Test;

public class CoinCalculatorServiceTest {
    private final CoinCalculatorService service = new CoinCalculatorService();

    @Test
    void testCalculateCoins_noCombination() {
        double target = 0.1;
        List<Double> coinDenominations = List.of(2.0, 5.0);
        List<Double> result = service.calculateCoins(target, coinDenominations);
        assertNull(result);
    }

    @Test
    void testCalculateCoins_providedExample1() {
        double target = 7.03;
        List<Double> coinDenominations = List.of(0.01, 0.5, 1.0, 5.0, 10.0);
        List<Double> result = service.calculateCoins(target, coinDenominations);
        assertNotNull(result);
        assertEquals(List.of(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), result);
    }

    @Test
    void testCalculateCoins_providedExample2() {
        double target = 103;
        List<Double> coinDenominations = List.of(1.0, 2.0, 50.0);
        List<Double> result = service.calculateCoins(target, coinDenominations);
        assertNotNull(result);
        assertEquals(List.of(1.0, 2.0, 50.0, 50.0), result);
    }

    @Test
    void testCalculateCoins_bestPossibleCombination() {
        double target = 11;
        List<Double> coinDenominations = List.of(2.0, 5.0);
        List<Double> result = service.calculateCoins(target, coinDenominations);
        assertNotNull(result);
        assertEquals(List.of(2.0, 2.0, 2.0, 5.0), result);
    }

    @Test
    void testGreedy_NoCombination() {
        double target = 0.1;
        List<Double> coinDenominations = List.of(2.0, 5.0);
        List<Double> result = service.greedy(target, coinDenominations);
        assertNull(result);
    }

    @Test
    void testGreedy_providedExample1() {
        double target = 7.03;
        List<Double> coinDenominations = List.of(0.01, 0.5, 1.0, 5.0, 10.0);
        List<Double> result = service.greedy(target, coinDenominations);
        assertEquals(List.of(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), result);
    }

    @Test
    void testGreedy_providedExample2() {
        double target = 103;
        List<Double> coinDenominations = List.of(1.0, 2.0, 50.0);
        List<Double> result = service.greedy(target, coinDenominations);
        assertEquals(List.of(1.0, 2.0, 50.0, 50.0), result);
    }

    @Test
    void testGreedy_GreedyCombination() {
        double target = 11;
        List<Double> coinDenominations = List.of(2.0, 5.0);
        List<Double> result = service.greedy(target, coinDenominations);
        assertEquals(List.of(5.0, 5.0), result);
    }
}
