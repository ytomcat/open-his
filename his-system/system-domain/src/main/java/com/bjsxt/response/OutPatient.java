package com.bjsxt.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName OutPatient.java
 * @Description TODO
 * open-his
 * @createTime 2022年04月05日 00:19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutPatient implements Serializable {
    @ApiModelProperty("门诊编号")
    private String value;
    @ApiModelProperty("门诊名称")
    @JSONField(name = "outpatient_name")
    private String label;
}
