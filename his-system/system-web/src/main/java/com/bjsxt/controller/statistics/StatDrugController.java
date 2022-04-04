package com.bjsxt.controller.statistics;

import cn.hutool.core.date.DateUtil;
import com.bjsxt.controller.BaseController;
import com.bjsxt.domain.Drug;
import com.bjsxt.domain.DrugStat;
import com.bjsxt.dto.DrugQueryDto;
import com.bjsxt.service.DrugService;
import com.bjsxt.vo.AjaxResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 尚学堂 雷哥
 * 药品销售统计控制器
 */
@RestController
@RequestMapping("statistics/drug")
public class StatDrugController extends BaseController {

    @Reference
    private DrugService drugService;

    /**
     * 查询发药统计列表
     */
    @GetMapping("queryDrug")
    public AjaxResult queryDrug(DrugQueryDto drugQueryDto){
        if(drugQueryDto.getBeginTime()==null){
            drugQueryDto.setQueryDate(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        }
        List<Drug> drugList=this.drugService.queryDrug(drugQueryDto);
        return AjaxResult.success(drugList);
    }


    /**
     * 查询发药数量统计列表
     */
    @GetMapping("queryDrugStat")
    public AjaxResult queryDrugStat(DrugQueryDto drugQueryDto){
        if(drugQueryDto.getBeginTime()==null){
            drugQueryDto.setQueryDate(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        }
        List<DrugStat> drugList=this.drugService.queryDrugStat(drugQueryDto);
        return AjaxResult.success(drugList);
    }
}
