package com.bjsxt.service.impl;

import com.bjsxt.domain.Workload;
import com.bjsxt.domain.WorkloadStat;
import com.bjsxt.dto.WorkloadQueryDto;
import com.bjsxt.mapper.WorkloadMapper;
import com.bjsxt.service.WorkloadService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
@Service
public class WorkloadServiceImpl implements WorkloadService {


    @Autowired
    private WorkloadMapper workloadMapper;

    @Override
    public List<Workload> queryWorkload(WorkloadQueryDto workloadQueryDto) {
        return this.workloadMapper.queryWorkload(workloadQueryDto);
    }

    @Override
    public List<WorkloadStat> queryWorkloadStat(WorkloadQueryDto workloadQueryDto) {
        return this.workloadMapper.queryWorkloadStat(workloadQueryDto);
    }
}
