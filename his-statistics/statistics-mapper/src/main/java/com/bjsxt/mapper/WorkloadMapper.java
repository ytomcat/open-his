package com.bjsxt.mapper;

import com.bjsxt.domain.Workload;
import com.bjsxt.domain.WorkloadStat;
import com.bjsxt.dto.WorkloadQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface WorkloadMapper {
    /**
     * 医生工作量统计列表
     *
     * @param workloadQueryDto
     * @return
     */
    List<Workload> queryWorkload(@Param("workload") WorkloadQueryDto workloadQueryDto);

    /**
     * 总体工作量统计列表
     *
     * @param workloadQueryDto
     * @return
     */
    List<WorkloadStat> queryWorkloadStat(@Param("workload") WorkloadQueryDto workloadQueryDto);
}
