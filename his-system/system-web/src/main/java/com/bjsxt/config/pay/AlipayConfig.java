package com.bjsxt.config.pay;

/**
 * @Author: 尚学堂 雷哥
 */
public class AlipayConfig {

    //应用的ID，申请时的APPID
    public static String app_id = "2021000118600903";

    //SHA256withRsa对应支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwMAbYb5f8BA+xM4JPXtzT5v/xC9AGUIbhyOt6+LQc0W9KeA3B+lvhjPTUljZ0MpDiYokYmOJDNBqce87kDnLHG7JlyBlhz+ZTtIUekhjUmw9VYJzKkA4kgOtrOIPb2D4ydCBFkgaVcHJ99Ndg2ONgIkl3IonvCn9GnPniUXXAekXBXaPi6/8ZAGY3RY5GBCKPLmTEBubuaqzB/1/ns/pYFQXYsRMqpAEdvFnrIRdmgw50bYSnZllb1twSDm1qYYJvPL+D3xXZXnLRpw+BNAVSMudWBPDnQCCt0vg+/i3jcpfHf1DQpUjXzZFqml93AFv5v1sRuIbUhxcukX/dbOxRwIDAQAB";
    //签名方式
    public static String sign_type = "RSA2";
    //字码编码格式
    public static String charset = "utf-8";
    //回调地址
    public static String notifyUrl = "http://4218j70y74.wicp.vip/pay/callback/";
}
