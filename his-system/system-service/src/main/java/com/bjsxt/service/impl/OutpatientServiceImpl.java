package com.bjsxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bjsxt.domain.Outpatient;
import com.bjsxt.mapper.DeptMapper;
import com.bjsxt.mapper.OutpatientMapper;
import com.bjsxt.response.BuilderDeptOutTree;
import com.bjsxt.service.OutpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName OutpatientServiceImpl.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月05日 15:11:00
 */
@Service
public class OutpatientServiceImpl implements OutpatientService {


    @Autowired
    private OutpatientMapper outpatientMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Outpatient> queryOutpatient() {
        LambdaQueryWrapper<Outpatient> wrapper = Wrappers.<Outpatient>lambdaQuery();
        return this.outpatientMapper.selectList(wrapper);
    }

    @Override
    public List<BuilderDeptOutTree> queryDeptTree() {
        return this.deptMapper.queryDeptTree();
    }
}
