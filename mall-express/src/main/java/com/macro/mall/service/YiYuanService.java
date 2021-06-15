package com.macro.mall.service;

import com.macro.mall.entity.ExpressBean;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public interface YiYuanService {
    /**
     *phone: 手机号后四位
     */
    public ExpressBean searchExpress(String expressNumber, String phone) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException;
}
