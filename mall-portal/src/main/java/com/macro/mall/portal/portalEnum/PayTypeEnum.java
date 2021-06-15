package com.macro.mall.portal.portalEnum;

public enum PayTypeEnum {

    PAYMONEY(0, "现金支付"), PAYPOINT(1, "积分兑换");

    private int payType;
    private String payName;

    PayTypeEnum(int payType, String payName) {
        this.payType = payType;
        this.payName = payName;
    }

    public int getPayType() {
        return payType;
    }

    public String getPayName() {
        return payName;
    }
}
