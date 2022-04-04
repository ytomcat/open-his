package com.bjsxt.controller.statistics;

import cn.hutool.core.date.DateUtil;
import com.bjsxt.controller.BaseController;
import com.bjsxt.dto.RevenueQueryDto;
import com.bjsxt.service.RevenueService;
import com.bjsxt.vo.AjaxResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: 尚学堂 雷哥
 *  收支统计控制器
 */
@RestController
@RequestMapping("statistics/revenue")
public class RevenueController extends BaseController {


    @Reference
    private RevenueService revenueService;


    /**
     * 收支统计总方法
     *  // 数组结构
     *       revenueObj: {
     *         totalRevenue: 0.00, // 合计收入
     *         overview: {// 收支概况: 总收入￥0 总退费￥0
     *           toll: 0.00,
     *           refund: 0.00
     *         },
     *         channel: {// 收入渠道: 现金支付￥0 支付宝支付￥0 现金退费￥0 支付宝退费￥0
     *           cashIncome: 0.00,
     *           alipayIncome: 0.00,
     *           cashRefund: 0.00,
     *           alipayRefund: 0.00
     *         }
     *       },
     *       // 声明图表的数据
     *       revenueOverview: { // 收支概况
     *         title: '收支概况',
     *         data: [
     *           { value: 320, name: '收费金额' },
     *           { value: 240, name: '退费金额' }
     *         ]
     *       },
     *       incomeChanel: { // 收入渠道
     *         title: '收入渠道',
     *         data: [
     *           { value: 4, name: '现金收入' },
     *           { value: 1, name: '支付宝收入' }
     *         ]
     *       },
     *       refund: { // 退款金额和渠道
     *         title: '退款',
     *         data: [
     *           { value: 200, name: '现金退费' },
     *           { value: 100, name: '支付宝退费' }
     *         ]
     *       }
     */
    @GetMapping("queryAllRevenueData")
    @HystrixCommand
    public AjaxResult queryAllRevenueData(RevenueQueryDto revenueQueryDto){
        //如果没有选择开始日期和结果日期，就查询当天的数据
        if(revenueQueryDto.getBeginTime()==null){
            revenueQueryDto.setQueryDate(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        }
        Map<String,Object> res=this.revenueService.queryAllRevenueData(revenueQueryDto);
        return AjaxResult.success(res);
    }
}
