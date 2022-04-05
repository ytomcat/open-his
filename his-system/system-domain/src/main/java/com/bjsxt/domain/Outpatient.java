package com.bjsxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName Outpatient.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月05日 15:00:00
 */
@ApiModel(value = "com-bjsxt-domain-Outpatient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_outpatient")
public class Outpatient {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "门诊编号")
    private Long id;

    @TableField(value = "outpatient_name")
    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;

    @TableField(value = "dept_id")
    @ApiModelProperty(value = "部门编号")
    private String deptId;

    @TableField(value = "gmt_create")
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
