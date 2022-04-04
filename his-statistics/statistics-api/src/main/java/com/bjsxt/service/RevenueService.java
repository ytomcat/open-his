package com.bjsxt.service;

import com.bjsxt.dto.RevenueQueryDto;

import java.util.Map;

/**
 * @Author: 尚学堂 雷哥
 */
public interface RevenueService {
    /**
     * 查询收支统计的数据
     * @param revenueQueryDto
     * @return
     */
    Map<String, Object> queryAllRevenueData(RevenueQueryDto revenueQueryDto);
}
