package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.UserInfo;

public interface WxMemberService {
    /**
     *微信登陆
     */
    public String wxLogin(String code, UserInfo rawData, String signature,
                          String encrypteData, String iv);
}
