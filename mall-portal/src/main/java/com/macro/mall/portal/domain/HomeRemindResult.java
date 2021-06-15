package com.macro.mall.portal.domain;

import com.macro.mall.model.SmsHomeRemind;
import lombok.Data;

import java.util.List;

@Data
public class HomeRemindResult {
    //温馨提醒
    private List<SmsHomeRemind> remindList;
}
