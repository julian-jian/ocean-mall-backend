package com.macro.mall.controller;



import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.MemberProductCollection;
import com.macro.mall.dto.MmsMemberInfoParam;
import com.macro.mall.model.UmsMember;
import com.macro.mall.service.MemberCollectionService;
import com.macro.mall.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberCollectionService memberCollectionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMember>> list(MmsMemberInfoParam queryParam,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMember> memberInfoList = memberInfoService.list(queryParam, pageSize, pageNum);

        return CommonResult.success(CommonPage.restPage(memberInfoList));
    }

    @RequestMapping(value = "/collection/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MemberProductCollection>> collectionList(MmsMemberInfoParam queryParam,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<MemberProductCollection> list = memberCollectionService.list(queryParam, pageNum, pageSize);
        if (list == null) {
            ArrayList<MemberProductCollection> list1 = new ArrayList<>();
            return CommonResult.success(CommonPage.restPage(list1));
        }
        return CommonResult.success(CommonPage.restPage(list));
    }
}
