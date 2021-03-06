package com.bjsxt.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 尚学堂 雷哥
 * 查询表单数据提交对象
 */
@ApiModel(value="com-bjsxt-dto-SchedulingQueryDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingQueryDto implements Serializable {

    private Long userId;//用户ID

    private Long deptId;//部门ID

    private Long outpatientId;//门诊ID

    //页面传过来的上一周  下一周的值
    private String queryDate;

    //要查询的周的开始日期和结束日期  根据queryDate去计算
    private String beginDate;
    private String endDate;


}
