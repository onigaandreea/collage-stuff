package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalOperations {

    List<BigDecimal> elems;

    public BigDecimalOperations(List<BigDecimal> elems) {
        this.elems = elems;
    }

    // Function to compute the sum of the elements using streams
    public BigDecimal computeSum(List<BigDecimal> numbers) {
        return numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Function to compute the average of the elements using streams
    public BigDecimal computeAverage(List<BigDecimal> numbers) {
        if (numbers.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(numbers.size()));
    }

    // Function to print the top 10% bigger numbers from the list using streams
    public List<BigDecimal> top10Percent(List<BigDecimal> numbers) {
        //return an empty list if there are less than 10 elements
        //10% out of 10 is 1, and we need at least one element in the result list
        if (numbers.size()<10) {
            return new ArrayList<>();
        }

        int topPercentCount = (int) (numbers.size() * 0.1);

        return numbers.stream()
                .sorted(BigDecimal::compareTo)
                .skip(Math.max(0, numbers.size() - topPercentCount)).toList();
    }
}
