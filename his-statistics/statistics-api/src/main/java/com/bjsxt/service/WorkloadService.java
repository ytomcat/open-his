package com.bjsxt.service;

import com.bjsxt.domain.Workload;
import com.bjsxt.domain.WorkloadStat;
import com.bjsxt.dto.WorkloadQueryDto;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface WorkloadService {
    /**
     * 医生工作量统计列表
     * @param workloadQueryDto
     * @return
     */
    List<Workload> queryWorkload(WorkloadQueryDto workloadQueryDto);

    /**
     * 总体工作量统计列表
     * @param workloadQueryDto
     * @return
     */
    List<WorkloadStat> queryWorkloadStat(WorkloadQueryDto workloadQueryDto);
}
