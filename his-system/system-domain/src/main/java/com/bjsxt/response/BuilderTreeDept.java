package com.bjsxt.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName TreeDepartment.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月04日 23:25:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "构建部门树形结构")
public class BuilderTreeDept implements Serializable {

    @ApiModelProperty("部门编号")
    private String value;
    @ApiModelProperty("部门名称")
    @JSONField(name = "special_name")
    private String label;
    @JSONField(name = "children")
    private List<OutPatient> children;


}
