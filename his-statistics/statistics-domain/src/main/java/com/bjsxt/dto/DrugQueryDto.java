package com.bjsxt.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: 尚学堂 雷哥
 */
@ApiModel(value="com-bjsxt-dto-DrugQueryDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DrugQueryDto  extends BaseDto{

    private String drugName;

    private String queryDate;
}
