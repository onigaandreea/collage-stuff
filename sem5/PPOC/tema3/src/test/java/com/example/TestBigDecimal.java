package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestBigDecimal {

    public BigDecimalOperations operations;

    @BeforeEach
    public void setup() {
        operations = new BigDecimalOperations(List.of(new BigDecimal("13.5"),new BigDecimal("15.55"),
                new BigDecimal("10.25"),new BigDecimal("3.57"),new BigDecimal("6.45"),
                new BigDecimal("13.05"),new BigDecimal("17.26"),new BigDecimal("23.25"),
                new BigDecimal("9.06"),new BigDecimal("20.95")));
    }

    @Test
    public void testSum(){
        BigDecimal result = operations.computeSum(operations.elems);
        Assertions.assertEquals(new BigDecimal("132.89"), result);
        BigDecimal result1 = operations.computeSum(operations.elems.subList(0,5));
        Assertions.assertEquals(new BigDecimal("49.32"), result1);
    }

    @Test
    public void testAvg(){
        BigDecimal result = operations.computeAverage(operations.elems);
        Assertions.assertEquals(new BigDecimal("13.289"), result);
        BigDecimal result1 = operations.computeAverage(operations.elems.subList(0,5));
        Assertions.assertEquals(new BigDecimal("9.864"), result1);
    }

    @Test
    public void testProcent(){
        List<BigDecimal> result = operations.top10Percent(operations.elems);
        Assertions.assertEquals(List.of(new BigDecimal("23.25")), result);
        List<BigDecimal> result1 = operations.top10Percent(operations.elems.subList(0,5));
        Assertions.assertEquals(List.of(), result1);
    }
}
