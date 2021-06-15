package com.macro.mall.portal.domain;

import lombok.Data;

@Data
public class AccessTokenVo {
    private String access_token;
    private int expires_in;
}
