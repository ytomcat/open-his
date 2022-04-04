package com.bjsxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjsxt.domain.Medicines;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 尚学堂 雷哥
 */

public interface MedicinesMapper extends BaseMapper<Medicines> {
    /**
     * 扣减库存
     *
     * @param medicinesId
     * @param num
     * @return
     */
    int deductionMedicinesStorage(@Param("medicinesId") Long medicinesId, @Param("num") long num);
}