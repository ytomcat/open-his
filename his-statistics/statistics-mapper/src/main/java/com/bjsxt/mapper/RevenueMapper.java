package com.bjsxt.mapper;

import com.bjsxt.domain.Income;
import com.bjsxt.domain.Refund;
import com.bjsxt.dto.RevenueQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface RevenueMapper {
    /**
     * 查询收入的数据
     *
     * @param revenueQueryDto
     * @return
     */
    List<Income> queryIncome(@Param("revenue") RevenueQueryDto revenueQueryDto);

    /**
     * 查询退费的数据
     *
     * @param revenueQueryDto
     * @return
     */
    List<Refund> queryRefund(@Param("revenue") RevenueQueryDto revenueQueryDto);
}
