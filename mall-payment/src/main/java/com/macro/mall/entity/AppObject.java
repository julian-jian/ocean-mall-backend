package com.macro.mall.entity;

import lombok.Data;

@Data
public class AppObject {
    //时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
    private String timeStamp;

    //随机字符串，长度为32个字符以下
    private String nonceStr;

    //统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
    private String app_package;

    //签名类型，默认为MD5
    private String signType;

    private String paySign;
}
