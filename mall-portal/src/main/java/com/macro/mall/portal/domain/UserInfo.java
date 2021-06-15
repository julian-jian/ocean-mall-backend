package com.macro.mall.portal.domain;

import lombok.Data;

@Data
public class UserInfo {
    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private int gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 地区
     */
    private String country;

    /**
     * 头像
     */
    private String avatarUrl;
}
