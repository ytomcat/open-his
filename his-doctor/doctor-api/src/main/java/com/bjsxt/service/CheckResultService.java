package com.bjsxt.service;

import com.bjsxt.domain.CheckResult;
import com.bjsxt.dto.CheckResultDto;
import com.bjsxt.dto.CheckResultFormDto;
import com.bjsxt.vo.DataGridView;

/**
 * @Author: 尚学堂 雷哥
 * 检查项目的接口
 */
public interface CheckResultService {
    /**
     * 保存检查的检查项目
     * @param checkResult
     * @return
     */
    int saveCheckResult(CheckResult checkResult);

    /**
     * 分页查询所有检查的项目
     * @param checkResultDto
     * @return
     */
    DataGridView queryAllCheckResultForPage(CheckResultDto checkResultDto);

    /**
     * 完成检查
     * @param checkResultFormDto
     * @return
     */
    int completeCheckResult(CheckResultFormDto checkResultFormDto);
}
