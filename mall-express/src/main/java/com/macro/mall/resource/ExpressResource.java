package com.macro.mall.resource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ExpressResource {
    //云市场分配的密钥Id
    @Value("${express.secretId}")
    String secretId;
    //云市场分配的密钥Key
    @Value("${express.secretKey}")
    String secretKey;
}
