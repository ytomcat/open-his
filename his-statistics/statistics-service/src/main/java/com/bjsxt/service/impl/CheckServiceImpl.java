package com.bjsxt.service.impl;

import com.bjsxt.domain.Check;
import com.bjsxt.domain.CheckStat;
import com.bjsxt.dto.CheckQueryDto;
import com.bjsxt.mapper.CheckMapper;
import com.bjsxt.service.CheckService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
@Service
public class CheckServiceImpl implements CheckService {


    @Autowired
    private CheckMapper checkMapper;

    @Override
    public List<Check> queryCheck(CheckQueryDto checkQueryDto) {
        return this.checkMapper.queryCheck(checkQueryDto);
    }

    @Override
    public List<CheckStat> queryCheckStat(CheckQueryDto checkQueryDto) {
        return this.checkMapper.queryCheckStat(checkQueryDto);
    }
}
