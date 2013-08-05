package com.xpsoft.oa.action.weixin;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xpsoft.core.web.action.BaseAction;

public class ConfirmAction extends BaseAction {

	public String weixin() throws Exception{
		// 获取请求和响应
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 获取请求参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostring = request.getParameter("echostr");
		String token = "lsq"; // 你自己填写的token
		// 对请求参数和自己的token进行排序，并连接排序后的结果为一个字符串
		String[] strSet = new String[] { token, timestamp, nonce };
		java.util.Arrays.sort(strSet);
		String total = "";
		for (String string : strSet) {
			total = total + string;
		}
		// SHA-1加密实例
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		sha1.update(total.getBytes());
		byte[] codedBytes = sha1.digest();
		String codedString = new BigInteger(1, codedBytes).toString(16);// 将加密后的字节数组转换成字符串。参见http://hi.baidu.com/aotori/item/c94813c4f15caa60f6c95d4a
		if (codedString.equals(signature)) { // 将加密的结果与请求参数中的signature比对，如果相同，原样返回echostr参数内容
			OutputStream os = response.getOutputStream();
			BufferedWriter resBr = new BufferedWriter(
					new OutputStreamWriter(os));
			resBr.write(echostring);
			resBr.flush();
			resBr.close();
		}
		return SUCCESS;
	}
}
