package com.bjsxt.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjsxt.constants.Constants;
import com.bjsxt.domain.CareOrderItem;
import com.bjsxt.domain.OrderBackfee;
import com.bjsxt.domain.OrderBackfeeItem;
import com.bjsxt.domain.OrderChargeItem;
import com.bjsxt.dto.OrderBackfeeDto;
import com.bjsxt.dto.OrderBackfeeFormDto;
import com.bjsxt.dto.OrderBackfeeItemDto;
import com.bjsxt.mapper.*;
import com.bjsxt.service.OrderBackfeeService;
import com.bjsxt.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 * 退费接口实现类
 */
@Service(methods = {@Method(name = "saveOrderAndItems",retries = 1),
        @Method(name = "backSuccess",retries = 1)})
public class OrderBackfeeServiceImpl implements OrderBackfeeService {

    @Autowired
    private OrderBackfeeMapper orderBackfeeMapper;

    @Autowired
    private OrderBackfeeItemMapper orderBackfeeItemMapper;

    @Autowired
    private OrderChargeMapper orderChargeMapper;

    @Autowired
    private OrderChargeItemMapper orderChargeItemMapper;

    @Autowired
    private CareOrderItemMapper careOrderItemMapper;

    @Override
    public void saveOrderAndItems(OrderBackfeeFormDto orderBackfeeFormDto) {
        OrderBackfeeDto orderBackfeeDto = orderBackfeeFormDto.getOrderBackfeeDto();
        List<OrderBackfeeItemDto> orderBackfeeItemDtoList = orderBackfeeFormDto.getOrderBackfeeItemDtoList();

        OrderBackfee orderBackfee=new OrderBackfee();
        BeanUtil.copyProperties(orderBackfeeDto,orderBackfee);
        orderBackfee.setBackStatus(Constants.ORDER_STATUS_0);
        orderBackfee.setCreateTime(DateUtil.date());
        orderBackfee.setCreateBy(orderBackfeeFormDto.getSimpleUser().getUserName());
        int i=this.orderBackfeeMapper.insert(orderBackfee);
        //保存详情
        for (OrderBackfeeItemDto orderBackfeeItemDto : orderBackfeeItemDtoList) {
            OrderBackfeeItem orderBackfeeItem=new OrderBackfeeItem();
            BeanUtil.copyProperties(orderBackfeeItemDto,orderBackfeeItem);
            //订单关联退费订单ID
            orderBackfeeItem.setBackId(orderBackfeeDto.getBackId());
            orderBackfeeItem.setStatus(Constants.ORDER_DETAILS_STATUS_0);
            this.orderBackfeeItemMapper.insert(orderBackfeeItem);
        }
    }

    @Override
    public void backSuccess(String backId, String backPlatformId, String backType) {
        //根据退费订单ID查询退费订单
        OrderBackfee orderBackfee = this.orderBackfeeMapper.selectById(backId);
        //设置平台交易编号
        orderBackfee.setBackPlatformId(backPlatformId);
        //设置退费类型
        orderBackfee.setBackType(backType);
        //设置退费时间
        orderBackfee.setBackTime(DateUtil.date());
        //修改订单状态
        orderBackfee.setBackStatus(Constants.ORDER_BACKFEE_STATUS_1);//已退费
        //更新订单状态
        this.orderBackfeeMapper.updateById(orderBackfee);
        //根据退费订单号查询退费订单详情
        QueryWrapper<OrderBackfeeItem> qw=new QueryWrapper<>();
        qw.eq(OrderBackfeeItem.COL_BACK_ID,backId);
        List<OrderBackfeeItem> orderBackfeeItems = this.orderBackfeeItemMapper.selectList(qw);
        List<String> allItemIds=new ArrayList<>();
        for (OrderBackfeeItem orderBackfeeItem : orderBackfeeItems) {
            allItemIds.add(orderBackfeeItem.getItemId());
        }
        //更新退费单的详情状态
        OrderBackfeeItem orderBackItemObj=new OrderBackfeeItem();
        orderBackItemObj.setStatus(Constants.ORDER_DETAILS_STATUS_2);//已退费
        QueryWrapper<OrderBackfeeItem> orderBackItemQw=new QueryWrapper<>();
        orderBackItemQw.in(OrderBackfeeItem.COL_ITEM_ID,allItemIds);
        this.orderBackfeeItemMapper.update(orderBackItemObj,orderBackItemQw);

        //更新收费详情的状态
        OrderChargeItem orderItemObj=new OrderChargeItem();
        orderItemObj.setStatus(Constants.ORDER_DETAILS_STATUS_2);//已退费
        QueryWrapper<OrderChargeItem> orderItemQw=new QueryWrapper<>();
        orderItemQw.in(OrderChargeItem.COL_ITEM_ID,allItemIds);
        this.orderChargeItemMapper.update(orderItemObj,orderItemQw);

        //更新处方详情的状态
        CareOrderItem careItemObj=new CareOrderItem();
        careItemObj.setStatus(Constants.ORDER_DETAILS_STATUS_2);//已退费
        QueryWrapper<CareOrderItem> careItemQw=new QueryWrapper<>();
        careItemQw.in(CareOrderItem.COL_ITEM_ID,allItemIds);
        this.careOrderItemMapper.update(careItemObj,careItemQw);
    }

    @Override
    public DataGridView queryAllOrderBackfeeForPage(OrderBackfeeDto orderBackfeeDto) {
        Page<OrderBackfee> page=new Page<>(orderBackfeeDto.getPageNum(),orderBackfeeDto.getPageSize());
        QueryWrapper<OrderBackfee> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(orderBackfeeDto.getPatientName()),OrderBackfee.COL_PATIENT_NAME,orderBackfeeDto.getPatientName());
        qw.like(StringUtils.isNotBlank(orderBackfeeDto.getRegId()),OrderBackfee.COL_REG_ID,orderBackfeeDto.getRegId());
        qw.orderByDesc(OrderBackfee.COL_CREATE_TIME);
        this.orderBackfeeMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public List<OrderBackfeeItem> queryrderBackfeeItemByBackId(String backId) {
        QueryWrapper<OrderBackfeeItem> qw=new QueryWrapper<>();
        qw.eq(OrderBackfeeItem.COL_BACK_ID, backId);
        return this.orderBackfeeItemMapper.selectList(qw);
    }

}
