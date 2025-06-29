package nus.iss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinCalculatorService {

    /**
     * https://www.youtube.com/watch?v=jgiZlGzXMBw
     * Change-making problem YouTube explanation
     * Using dynamic programming
     */
    public List<Double> calculateCoins(double target, List<Double> coinDenominations) {

        // scale target and coinDenominations by 100x (get rid of decimals)
        int scale = 100;
        int scaledTarget = (int) Math.round(target * scale);
        int[] scaledCoinDenominations =
                coinDenominations.stream()
                        .mapToInt(denomination -> (int) Math.round(denomination * scale))
                        .sorted()
                        .toArray();

        // initialise 2 arrays with range from 0 to scaledTarget
        // For values from 0 to scaledTarget
        // minCoins will store the minimum amount of coins (based on provided denominations)
        // to achieve the values
        int[] minCoins = new int[scaledTarget + 1];

        // coinThatAchievedMinCoins stores the coin denomination
        // that allowed the minimum amount of coins to achieve the values
        int[] coinThatAchievedMinCoins = new int[scaledTarget + 1];

        // fill minCoins with arbitrary values (impossible minimum amount of coins)
        Arrays.fill(minCoins, scaledTarget + 1);

        // to reach value of 0, 0 coins needed
        minCoins[0] = 0;

        for (int i = 1; i < scaledTarget + 1; i++) {
            for (int scaledCoin : scaledCoinDenominations) {
                if (i >= scaledCoin) {
                    if (minCoins[i - scaledCoin] + 1 < minCoins[i]) {
                        minCoins[i] = minCoins[i - scaledCoin] + 1;
                        coinThatAchievedMinCoins[i] = scaledCoin;
                    }
                }
            }
        }

        // if minimum amount of coins to achieve value (scaledTarget in this case)
        // means no solution, the provided coin denominations unable to reach target perfectly
        // use greedy algorithm instead
        if (minCoins[scaledTarget] == scaledTarget + 1) {
            return greedy(target, coinDenominations);
        }

        // Track back which coins helped achieved minimum amount of coins
        // unscale coin denominations and add to coinList
        List<Double> coinList = new ArrayList<>();
        int remainder = scaledTarget;
        while (remainder > 0) {
            double unscaledCoin = (double) coinThatAchievedMinCoins[remainder] / scale;
            coinList.add(unscaledCoin);
            remainder -= coinThatAchievedMinCoins[remainder];
        }

        return coinList.stream().sorted().toList();
    }

    public List<Double> greedy(double target, List<Double> coinsDenominations) {

        List<Double> coinList = new ArrayList<>();
        List<Double> reversedSortedCoinDenominations =
                coinsDenominations.stream()
                        .sorted((coin1, coin2) -> coin2.compareTo(coin1))
                        .toList();

        // take largest coin denomination first, add to coinList until the remainder < denomination
        // then go to next smaller denomination and repeat.
        double remainder = target;
        for (double coin : reversedSortedCoinDenominations) {
            while (remainder >= coin) {
                coinList.add(coin);
                remainder -= coin;
            }
        }

        if (coinList.isEmpty()) {
            return null;
        }

        return coinList.reversed();
    }
}
