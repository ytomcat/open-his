package com.bjsxt.controller.doctor;

import cn.hutool.core.bean.BeanUtil;
import com.bjsxt.constants.Constants;
import com.bjsxt.domain.CareHistory;
import com.bjsxt.domain.CareOrder;
import com.bjsxt.domain.CareOrderItem;
import com.bjsxt.service.CareService;
import com.bjsxt.vo.AjaxResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: 尚学堂 雷哥
 * @Description: 发药控制器
 */
@RestController
@RequestMapping("doctor/handleMedicine")
public class HandleMedicineController {

    @Reference
    private CareService careService;

    /**
     * 根据挂号ID查询未支付的处方信息及详情
     */
    @GetMapping("getChargedCareHistoryOnlyMedicinesByRegId/{regId}")
    @HystrixCommand
    public AjaxResult getChargedCareHistoryOnlyMedicinesByRegId(@PathVariable String regId){
        //声明返回的Map对象
        Map<String,Object> res=new HashMap<>();
        //根据挂号单ID查询病例信息
        CareHistory careHistory=this.careService.queryCareHistoryByRegId(regId);
        if(null==careHistory){
            return AjaxResult.fail("【"+regId+"】的挂号单没有对应的病例信息，请核对后再查询");
        }
        //放入默认值
        res.put("careHistory",careHistory);
        res.put("careOrders", Collections.EMPTY_LIST);
        //声明一个可以存放careOrders的集合
        List<Map<String,Object>> mapList=new ArrayList<>();
        //根据病例编号查询处方
        List<CareOrder> careOrders=this.careService.queryCareOrdersByChId(careHistory.getChId());
        if(careOrders.isEmpty()){
            return AjaxResult.fail("【"+regId+"】的挂号单没相关的处方信息，请核对后再查询");
        }
        for (CareOrder careOrder : careOrders) {
            //只需要药用处方法
            if(careOrder.getCoType().equals(Constants.CO_TYPE_MEDICINES)){
                Map<String,Object> beanToMap= BeanUtil.beanToMap(careOrder);
                beanToMap.put("careOrderItems", Collections.EMPTY_LIST);
                BigDecimal allAmount=new BigDecimal("0");
                //根据处方ID查询未支持的处方详情列表
                List<CareOrderItem> careOrderItems=this.careService.queryCareOrderItemsByCoId(careOrder.getCoId(), Constants.ORDER_DETAILS_STATUS_1);
                //如果当前处方未支付的详情为空 结束当前循环
                if(careOrderItems.isEmpty()){
                    continue;
                }else{
                    //重新计算总价
                    for (CareOrderItem careOrderItem : careOrderItems) {
                        allAmount=allAmount.add(careOrderItem.getAmount());
                    }
                    beanToMap.put("careOrderItems",careOrderItems);
                    beanToMap.put("allAmount",allAmount);
                    mapList.add(beanToMap);
                }
            }
        }
        if(mapList.isEmpty()){
            return AjaxResult.fail("【"+regId+"】的挂号单没已支付的药品处方信息，请核对后再查询");
        }else{
            res.put("careOrders",mapList);
            return AjaxResult.success(res);
        }
    }


    /**
     * 发药
     */
    @PostMapping("doMedicine")
    public AjaxResult doMedicine(@RequestBody List<String> itemIds){
        if(itemIds==null||itemIds.isEmpty()){
            return AjaxResult.fail("请选择要发药的药品项");
        }
        //执行发药  情况1  库存够正常走   情况2 库存不够  返回哪个药品库存不够，只发够的药品  其它的让患者自己去退费
        String msg=this.careService.doMedicine(itemIds);
        if(StringUtils.isBlank(msg)){
            return AjaxResult.success();
        }else{
            return AjaxResult.fail(msg);
        }
    }

}
