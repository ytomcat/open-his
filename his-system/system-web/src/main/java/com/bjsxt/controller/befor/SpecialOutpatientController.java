package com.bjsxt.controller.befor;

import com.bjsxt.domain.Outpatient;
import com.bjsxt.response.BuilderDeptOutTree;
import com.bjsxt.response.BuilderTreeDept;
import com.bjsxt.service.DeptService;
import com.bjsxt.service.OutpatientService;
import com.bjsxt.vo.AjaxResult;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName SpecialOutpatientController.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月04日 23:34:00
 */
@RestController
@RequestMapping("befor/specialOutpatient")
public class SpecialOutpatientController {

    @Autowired
    private DeptService deptService;




    @Autowired
    private OutpatientService outpatientService;

    @GetMapping("/builderTreeDept")
    @ApiModelProperty(value = "构建部门树形结构")
    public AjaxResult builderTreeDept() {
        List<BuilderTreeDept> builderTreeDept = this.deptService.builderTreeDept();
        return AjaxResult.success("查询成功", builderTreeDept);
    }

    @GetMapping("/queryOutpatient")
    @ApiModelProperty(value = "查询门诊表信息")
    public AjaxResult queryOutpatient() {
        List<Outpatient> outpatientList = this.outpatientService.queryOutpatient();
        return AjaxResult.success("查询成功", outpatientList);
    }

    @GetMapping("/queryDeptTree")
    @ApiModelProperty(value = "查询部门信息构造树形结构")
    public AjaxResult queryDeptTree() {
        List<BuilderDeptOutTree> outpatientList = this.outpatientService.queryDeptTree();
        Map<String, List<BuilderDeptOutTree>> map = outpatientList.stream().collect(Collectors.groupingBy(BuilderDeptOutTree::getDeptName));
        return AjaxResult.success("查询成功", map);
    }

}
