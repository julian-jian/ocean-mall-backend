package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsCompanyAddress;
import com.macro.mall.model.OmsOrderReturnApply;
import lombok.Data;

@Data
public class OrderReturnTo extends OmsOrderReturnApply {
    private OmsCompanyAddress companyAddress;
}
