package com.bjsxt.controller.doctor;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.bjsxt.config.pay.AlipayConfig;
import com.bjsxt.constants.Constants;
import com.bjsxt.service.OrderChargeService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 尚学堂 雷哥
 */
@RestController
@RequestMapping("pay")
@Log4j2
public class PayController {

    @Reference
    private OrderChargeService orderChargeService;

    @RequestMapping("callback/{orderId}")
    public void callback(@PathVariable String orderId, HttpServletRequest request) {
        Map<String, String> parameterMap = this.getParameterMap(request);
        String trade_status = parameterMap.get("trade_status");
        if ("TRADE_SUCCESS".equals(trade_status)) {
            try {
                //验证是否为支付宝发来的信息
                boolean singVerified = AlipaySignature.rsaCheckV1(parameterMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
                System.out.println(singVerified);//只有支付宝调用我们系统的接口才能去更新系统订单状态
                log.info("验证签名结果{}:" + singVerified);
                String refund_fee = parameterMap.get("refund_fee");
                if (StringUtils.isNotBlank(refund_fee)) {
                    //说明是退费
                    System.out.println("退费成功：退费的子订单ID为：" + parameterMap.get("out_biz_no"));
                    //更新订单状态
                } else {
                    //说明支付
                    String trade_no = parameterMap.get("trade_no");
                    System.out.println("收费成功，平台ID" + trade_no);
                    orderChargeService.paySuccess(orderId,trade_no, Constants.PAY_TYPE_1);
                }
                System.out.println(JSON.toJSON(parameterMap));
            } catch (AlipayApiException e) {
                e.printStackTrace();
                log.error(orderId + "验证签名出现异常");
            }
        }else if("WAIT_BUYER_PAY".equals(trade_status)){
            System.out.println("二维码已扫描，等待支付");
        }
    }
    /**
     * 获取request中的参数集合转Map
     * Map<String,String> parameterMap = RequestUtil.getParameterMap(request)
     *
     * @param request
     * @return
     */
    public Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}
