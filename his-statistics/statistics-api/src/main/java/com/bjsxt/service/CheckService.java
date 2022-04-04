package com.bjsxt.service;

import com.bjsxt.domain.Check;
import com.bjsxt.domain.CheckStat;
import com.bjsxt.dto.CheckQueryDto;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface CheckService {
    /**
     * 查询检查项列表
     * @param checkQueryDto
     * @return
     */
    List<Check> queryCheck(CheckQueryDto checkQueryDto);

    /**
     * 查询检查项统计列表
     * @param checkQueryDto
     * @return
     */
    List<CheckStat> queryCheckStat(CheckQueryDto checkQueryDto);
}
