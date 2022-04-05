package com.bjsxt.service;

import com.bjsxt.domain.Outpatient;
import com.bjsxt.response.BuilderDeptOutTree;

import java.util.List;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName OutpatientService.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月05日 15:10:00
 */
public interface OutpatientService {
     List<Outpatient> queryOutpatient();

     List<BuilderDeptOutTree> queryDeptTree();
}
