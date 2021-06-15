package com.macro.mall.bo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class WxTest {
    @Value("${wx.sss}")
    private String wx;
}
