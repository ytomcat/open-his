package com.bjsxt.mapper;

import com.bjsxt.domain.Drug;
import com.bjsxt.domain.DrugStat;
import com.bjsxt.dto.DrugQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface DrugMapper {
    /**
     * 药品统计列表
     *
     * @param drugQueryDto
     * @return
     */
    List<Drug> queryDrug(@Param("drug") DrugQueryDto drugQueryDto);

    /**
     * 药品数量统计列表
     *
     * @param drugQueryDto
     * @return
     */
    List<DrugStat> queryDrugStat(@Param("drug") DrugQueryDto drugQueryDto);
}
