package com.macro.mall.portal.domain;

import lombok.Data;

@Data
public class WxDataTo {
    private String code;
    private String rawData;
    private String signature;
    private String encrypteData;
    private String iv;
}
