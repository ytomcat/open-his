package com.bjsxt.service.impl;

import com.bjsxt.domain.Drug;
import com.bjsxt.domain.DrugStat;
import com.bjsxt.dto.DrugQueryDto;
import com.bjsxt.mapper.DrugMapper;
import com.bjsxt.service.DrugService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
@Service
public class DrugServiceImpl implements DrugService {


    @Autowired
    private DrugMapper drugMapper;

    @Override
    public List<Drug> queryDrug(DrugQueryDto drugQueryDto) {
        return this.drugMapper.queryDrug(drugQueryDto);
    }

    @Override
    public List<DrugStat> queryDrugStat(DrugQueryDto drugQueryDto) {
        return this.drugMapper.queryDrugStat(drugQueryDto);
    }
}
