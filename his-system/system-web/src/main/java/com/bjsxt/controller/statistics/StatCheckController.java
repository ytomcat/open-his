package com.bjsxt.controller.statistics;

import cn.hutool.core.date.DateUtil;
import com.bjsxt.controller.BaseController;
import com.bjsxt.domain.Check;
import com.bjsxt.domain.CheckStat;
import com.bjsxt.dto.CheckQueryDto;
import com.bjsxt.service.CheckService;
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
@RequestMapping("statistics/check")
public class StatCheckController extends BaseController {

    @Reference
    private CheckService checkService;

    /**
     * 查询检查项列表
     */
    @GetMapping("queryCheck")
    public AjaxResult queryCheck(CheckQueryDto checkQueryDto){
        if(checkQueryDto.getBeginTime()==null){
            checkQueryDto.setQueryDate(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        }
        List<Check> checkList=this.checkService.queryCheck(checkQueryDto);
        return AjaxResult.success(checkList);
    }


    /**
     * 查询检查项统计列表
     */
    @GetMapping("queryCheckStat")
    public AjaxResult queryCheckStat(CheckQueryDto checkQueryDto){
        if(checkQueryDto.getBeginTime()==null){
            checkQueryDto.setQueryDate(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        }
        List<CheckStat> checkList=this.checkService.queryCheckStat(checkQueryDto);
        return AjaxResult.success(checkList);
    }
}
