package com.bjsxt.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: 尚学堂 雷哥
 */
@ApiModel(value="com-bjsxt-dto-CheckQueryDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckQueryDto extends BaseDto{

    private String patientName;

    private String checkItemId;

    private String queryDate;
}
