package com.bjsxt.response;

import com.bjsxt.domain.Dept;
import com.bjsxt.domain.Outpatient;
import com.bjsxt.dto.DeptDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName BuilderDeptOutTree.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月05日 18:10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuilderDeptOutTree extends Dept{
    private List<Outpatient> children;
}
