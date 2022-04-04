package com.bjsxt.service;

import com.bjsxt.domain.Drug;
import com.bjsxt.domain.DrugStat;
import com.bjsxt.dto.DrugQueryDto;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface DrugService {
    /**
     * 查询发药统计列表
     * @param drugQueryDto
     * @return
     */
    List<Drug> queryDrug(DrugQueryDto drugQueryDto);

    /**
     * 查询发药数量统计列表
     * @param drugQueryDto
     * @return
     */
    List<DrugStat> queryDrugStat(DrugQueryDto drugQueryDto);
}
