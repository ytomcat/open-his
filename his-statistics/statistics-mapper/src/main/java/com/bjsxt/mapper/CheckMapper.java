package com.bjsxt.mapper;

import com.bjsxt.domain.Check;
import com.bjsxt.domain.CheckStat;
import com.bjsxt.dto.CheckQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface CheckMapper {
    /**
     * 查询检查项列表
     *
     * @param checkQueryDto
     * @return
     */
    List<Check> queryCheck(@Param("check") CheckQueryDto checkQueryDto);

    /**
     * 查询检查项统计列表
     *
     * @param checkQueryDto
     * @return
     */
    List<CheckStat> queryCheckStat(@Param("check") CheckQueryDto checkQueryDto);
}
