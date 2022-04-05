package com.bjsxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjsxt.domain.Dept;
import com.bjsxt.response.BuilderDeptOutTree;
import com.bjsxt.response.BuilderTreeDept;

import java.util.List;

/**
* @Author: 尚学堂 雷哥
*/

public interface DeptMapper extends BaseMapper<Dept> {
    List<BuilderTreeDept> builderTreeDept();

    List<BuilderDeptOutTree> queryDeptTree();

}