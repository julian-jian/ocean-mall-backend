package com.macro.mall.portal.service;

import org.springframework.stereotype.Service;

@Service
public interface QrCodeService {
    public String getQr(String path, String scene, int width, Long productId);
}
