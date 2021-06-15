package com.macro.mall.portal.controller;
import com.alibaba.fastjson.JSON;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.UserInfo;
import com.macro.mall.portal.domain.WxDataTo;
import com.macro.mall.portal.service.WxMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 微信会员登录管理Controller
 */
@Controller
@Api(tags = "微信登录管理", description = "微信登录管理")
@RequestMapping("/wx")
public class WxMemberController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private WxMemberService wxMemberService;

    private static final Logger log =  LoggerFactory.getLogger(WxMemberController.class);


    @ApiOperation("微信登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody WxDataTo wxDataTo) {
        String token = wxMemberService.wxLogin(wxDataTo.getCode(), JSON.parseObject(wxDataTo.getRawData(), UserInfo.class),
                wxDataTo.getSignature(), wxDataTo.getEncrypteData(), wxDataTo.getIv());
        if (token == null) {
            return CommonResult.validateFailed("");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        //用于统计某一天所有登陆次数
        log.info("用户登陆");
        return CommonResult.success(tokenMap);
    }
}
