package com.bjsxt.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * @Author: 尚学堂 雷哥
 */

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class DrugStat  extends BaseEntity {
    /**
     * 药品id
     */
    private String medicinesId;

    /**
     * 药品名
     */
    private String medicinesName;

    /**
     * 金额
     */
    private BigDecimal totalAmount;

    /**
     * 销售数量
     */
    private BigDecimal count;

}
