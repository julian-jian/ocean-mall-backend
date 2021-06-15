package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.QrParam;
import com.macro.mall.portal.service.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(tags = "获取二维码", description = "获取二维码")
@RequestMapping("/qr")
public class QrCodeController {
    @Autowired
    QrCodeService qrCodeService;

    @ApiOperation("获取二维码")
    @RequestMapping(value = "/qrCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> generateQrCode(@RequestBody QrParam qrParam) {
        String qr = qrCodeService.getQr(qrParam.getPath(), qrParam.getScene(), qrParam.getWidth(), Long.parseLong(qrParam.getProductId()));
        if (qr == null) {
            CommonResult.failed();
        }
        return CommonResult.success(qr);
    }
}
