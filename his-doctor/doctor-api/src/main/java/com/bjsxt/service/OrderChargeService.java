package com.bjsxt.service;

import com.bjsxt.domain.OrderCharge;
import com.bjsxt.domain.OrderChargeItem;
import com.bjsxt.dto.OrderChargeDto;
import com.bjsxt.dto.OrderChargeFromDto;
import com.bjsxt.vo.DataGridView;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 */
public interface OrderChargeService {

    /**
     * 保存收费订单及详情
     * @param orderChargeFromDto
     */
    void saveOrderAndItems(OrderChargeFromDto orderChargeFromDto);

    /**
     * 支付成功之后更新订单状态
     * @param orderId
     * @param payPlatformId 平台交易ID 如果是现金，则为空
     * @param  payType 支付类型
     */
    void paySuccess(String orderId, String payPlatformId,String payType);

    /**
     * 根据订单ID查询订单信息
     * @param orderId
     * @return
     */
    OrderCharge queryOrderChargeByOrderId(String orderId);

    /**
     * 分页查询所有收费单
     * @param orderChargeDto
     * @return
     */
    DataGridView queryAllOrderChargeForPage(OrderChargeDto orderChargeDto);

    /**
     * 据收费单的ID查询收费详情信息
     * @param orderId
     * @return
     */
    List<OrderChargeItem> queryOrderChargeItemByOrderId(String orderId);

    /**
     * 根据详情ID查询收费订单详情
     * @param itemId
     * @return
     */
    OrderChargeItem queryOrderChargeItemByItemId(String itemId);
}
