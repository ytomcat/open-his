package com.bjsxt.pay;

/**
 * @author yuanfeng
 * @version 1.0.0
 * @ClassName TestRefund.java
 * @Description TODO
 * open-his
 * @createTime 2021年08月11日 20:25:00
 */
public class TestRefund {
    public static void main(String[] args) {
        String outTradeNo = "yf123124124123412312312";
        String tradeNo = "2021081122001493200501407287";
        String subject = "SXT-医疗管理系统支付平台";
        String refudAmount = "30";
        String refudReason = "不想要了";
        PayService.payBack(outTradeNo, tradeNo, refudAmount, refudReason, "BK12345679");
    }
}
