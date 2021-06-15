package com.macro.mall.portal.service;

import com.macro.mall.model.PmsProduct;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PointService {

    List<PmsProduct> getPointList(Integer pageNum, Integer pageSize, Integer min, Integer max);
}
