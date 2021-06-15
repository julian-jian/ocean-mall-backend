package com.macro.mall;

import com.macro.mall.service.HomeDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDataTest {
    @Autowired
    HomeDataService homeDataService;

    @Test
    public void homeDataServiceTest() {
        System.out.printf(homeDataService.getOrderData().toString());
    }

    @Test
    public void productDataTest() {
        System.out.println(homeDataService.getProductData().toString());
    }

    @Test
    public void memberDataTest() {
        System.out.println(homeDataService.getMemberData().toString());
    }
}
