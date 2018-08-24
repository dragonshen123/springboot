/**
 * File Name:WxPayConfig.java
 * Date:2017年10月24日上午11:51:26
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package utils;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 微信相关参数配置
 * ClassName:WxPayConfig 
 * Date:     2017年10月24日 上午11:51:26 
 * @author   sqq 
 * @since    JDK 1.8 
 */
public class WxPayConfig {
	
	//=======【基本信息设置】=====================================
	//微信公众号身份的唯一标识。审核通过后，在微信发送的邮件中查看
	public static String APPID ;//需改
	//受理商ID，身份标识
	public static  String MCHID ;//需改
	//商户支付密钥Key。审核通过后，在微信发送的邮件中查看
	public static String KEY ;//需改
	//JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看
	public static String APPSECRET ;//需改
	
	
	//=======【异步通知url设置】===================================
	//异步通知url，商户根据实际开发过程设定
	
	public static  String SERVER_URL;//需改
	
	public static String NOTIFY_URL ;

	
	//编码方式
	public static final String ENCODE = "UTF-8";
	
	//微信uri-根据code获取openid
	public static String GET_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	
	//微信uri-请求预支付接口
	public static String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}

