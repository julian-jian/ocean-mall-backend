package com.macro.mall.payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(111.12);
        String price = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
        System.out.println(price);
    }
}
