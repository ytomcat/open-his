package com.bjsxt.pay;

import java.util.Map;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName TestPay.java
 * @Description TODO
 * open-his
 * @createTime 2021年08月11日 16:32:00
 */
public class TestPay {
    public static void main(String[] args) {
        String outTradeNo = "yf123124124123412312312";
        String subject = "SXT-医疗管理系统支付平台";
        String totalAmount = "100";
        String undiscountableAmount = null;
        String body = "买药用的";
        String notifyUrl = "http://4218j70y74.wicp.vip/pay/callback/" + outTradeNo;
        Map<String, Object> pay = PayService.pay(outTradeNo, subject, totalAmount, undiscountableAmount, body, notifyUrl);
        System.out.println(pay);
    }
}
