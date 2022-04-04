package com.bjsxt.controller.doctor;

import cn.hutool.core.date.DateUtil;
import com.bjsxt.constants.Constants;
import com.bjsxt.controller.BaseController;
import com.bjsxt.domain.CareHistory;
import com.bjsxt.domain.CareOrder;
import com.bjsxt.domain.CareOrderItem;
import com.bjsxt.domain.CheckResult;
import com.bjsxt.dto.CheckResultDto;
import com.bjsxt.dto.CheckResultFormDto;
import com.bjsxt.service.CareService;
import com.bjsxt.service.CheckResultService;
import com.bjsxt.utils.ShiroSecurityUtils;
import com.bjsxt.vo.AjaxResult;
import com.bjsxt.vo.DataGridView;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 尚学堂 雷哥
 * @Description: 检查管理控制器
 */
@RestController
@RequestMapping("doctor/check")
public class CheckResultController extends BaseController {

    @Reference
    private CareService careService;

    @Reference
    private CheckResultService checkResultService;

    /**
     * 根据挂号ID查询未支付的处方信息及详情
     */
    @PostMapping("queryNeedCheckItem")
    @HystrixCommand
    public AjaxResult queryNeedCheckItem(@RequestBody CheckResultDto checkResultDto){
        //声明返回的对象
        List<CareOrderItem> resCareOrderItems=new ArrayList<>();
        if(StringUtils.isNotBlank(checkResultDto.getRegId())){
            //查询时带入挂号单
            //根据挂号单ID查询病例信息
            CareHistory careHistory=this.careService.queryCareHistoryByRegId(checkResultDto.getRegId());
            if(null==careHistory){
                return AjaxResult.success(resCareOrderItems);
            }
            //根据病例ID查询所有的处方信息
            List<CareOrder> careOrders=this.careService.queryCareOrdersByChId(careHistory.getChId());
            for (CareOrder careOrder : careOrders) {
                if(careOrder.getCoType().equals(Constants.CO_TYPE_CHECK)){//只取检查处方
                    List<CareOrderItem> careOrderItems = this.careService.queryCareOrderItemsByCoId(careOrder.getCoId(), Constants.ORDER_DETAILS_STATUS_1);
                    //过滤查询条件
                    for (CareOrderItem careOrderItem : careOrderItems) {
                        if(checkResultDto.getCheckItemIds().contains(Integer.valueOf(careOrderItem.getItemRefId()))){
                            resCareOrderItems.add(careOrderItem);
                        }
                    }
                }
            }
            return AjaxResult.success(resCareOrderItems);
        }else{
            //查询所有已支付检查的项目
           List<CareOrderItem> careOrderItems= this.careService.queryCareOrderItemsByStatus(Constants.CO_TYPE_CHECK,Constants.ORDER_DETAILS_STATUS_1);
            //过滤查询条件
            for (CareOrderItem careOrderItem : careOrderItems) {
                if(checkResultDto.getCheckItemIds().contains(Integer.valueOf(careOrderItem.getItemRefId()))){
                    resCareOrderItems.add(careOrderItem);
                }
            }
            return AjaxResult.success(resCareOrderItems);
        }
    }

    /**
     * 根据检查单号查询要检查的项目详情
     */
    @GetMapping("queryCheckItemByItemId/{itemId}")
    @HystrixCommand
    public AjaxResult queryCheckItemByItemId(@PathVariable String itemId){
        //根据详情ID查询详情对象
        CareOrderItem careOrderItem = this.careService.queryCareOrderItemByItemId(itemId);
        if(careOrderItem==null){
            return AjaxResult.fail("【"+itemId+"】的检查单号的数据不存在，请核对后再查询");
        }
        if(!careOrderItem.getStatus().equals(Constants.ORDER_DETAILS_STATUS_1)){
            return AjaxResult.fail("【"+itemId+"】的检查单号没有支付，请支付后再查询");
        }
        if(!careOrderItem.getItemType().equals(Constants.CO_TYPE_CHECK)){
            return AjaxResult.fail("【"+itemId+"】的单号不是检查项目，请核对再查询");
        }
        CareOrder careOrder=this.careService.queryCareOrdersByCoId(careOrderItem.getCoId());
        CareHistory careHistory=this.careService.queryCareHistoryByChId(careOrder.getChId());
        Map<String,Object> res=new HashMap<>();
        res.put("item",careOrderItem);
        res.put("careOrder",careOrder);
        res.put("careHistory",careHistory);
        return AjaxResult.success(res);
    }

    /***
     * 开始检查
     */
    @PostMapping("startCheck/{itemId}")
    @HystrixCommand
    public AjaxResult startCheck(@PathVariable String itemId){
        //根据详情ID查询详情对象
        CareOrderItem careOrderItem = this.careService.queryCareOrderItemByItemId(itemId);
        if(careOrderItem==null){
            return AjaxResult.fail("【"+itemId+"】的检查单号的数据不存在，请核对后再查询");
        }
        if(!careOrderItem.getStatus().equals(Constants.ORDER_DETAILS_STATUS_1)){
            return AjaxResult.fail("【"+itemId+"】的检查单号没有支付，请支付后再查询");
        }
        if(!careOrderItem.getItemType().equals(Constants.CO_TYPE_CHECK)){
            return AjaxResult.fail("【"+itemId+"】的单号不是检查项目，请核对再查询");
        }
        CareOrder careOrder=this.careService.queryCareOrdersByCoId(careOrderItem.getCoId());
        CareHistory careHistory=this.careService.queryCareHistoryByChId(careOrder.getChId());
        //构建要保存到数据库的对象
        CheckResult checkResult=new CheckResult();
        checkResult.setItemId(itemId);
        checkResult.setCheckItemId(Integer.valueOf(careOrderItem.getItemRefId()));
        checkResult.setCheckItemName(careOrderItem.getItemName());
        checkResult.setPatientId(careOrder.getPatientId());
        checkResult.setPatientName(careOrder.getPatientName());
        checkResult.setPrice(careOrderItem.getPrice());
        checkResult.setRegId(careHistory.getRegId());
        checkResult.setResultStatus(Constants.RESULT_STATUS_0);//检查中
        checkResult.setCreateTime(DateUtil.date());
        checkResult.setCreateBy(ShiroSecurityUtils.getCurrentUserName());
        return AjaxResult.toAjax(this.checkResultService.saveCheckResult(checkResult));
    }


    /**
     * 查询所有检查中的项目
     */
    @PostMapping("queryAllCheckingResultForPage")
    @HystrixCommand
    public AjaxResult queryAllCheckingResultForPage(@RequestBody CheckResultDto checkResultDto){
        checkResultDto.setResultStatus(Constants.RESULT_STATUS_0);//检查中的项目
        DataGridView dataGridView=this.checkResultService.queryAllCheckResultForPage(checkResultDto);
        return AjaxResult.success("查询成功",dataGridView.getData(),dataGridView.getTotal());
    }

    /**
     *完成检查并保存上传结果
     */
    @PostMapping("completeCheckResult")
    @HystrixCommand
    public AjaxResult completeCheckResult(@RequestBody @Validated CheckResultFormDto checkResultFormDto){
        checkResultFormDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.checkResultService.completeCheckResult(checkResultFormDto));
    }
    /**
     * 查询参与检查的项目
     */
    @PostMapping("queryAllCheckResultForPage")
    @HystrixCommand
    public AjaxResult queryAllCheckResultForPage(@RequestBody CheckResultDto checkResultDto){
        DataGridView dataGridView=this.checkResultService.queryAllCheckResultForPage(checkResultDto);
        return AjaxResult.success("查询成功",dataGridView.getData(),dataGridView.getTotal());
    }
}
