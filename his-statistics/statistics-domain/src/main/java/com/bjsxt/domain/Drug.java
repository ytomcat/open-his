package com.bjsxt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Drug  extends BaseEntity {
    /**
     * 药品id
     */
    private String medicinesId;

    /**
     * 药品名
     */
    private String medicinesName;

    /**
     * 订单数量
     */
    private BigDecimal num;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createTime;
}
