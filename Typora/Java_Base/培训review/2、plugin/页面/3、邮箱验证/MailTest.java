package com.igeek.user.mainapp;

import java.util.Date;

import org.junit.Test;

import com.igeek.user.utils.MailUtils;

public class MailTest {
	
	@Test
	public void userA2B(){
		String from = "aaa@igeek.com";
		String to = "bbb@igeek.com";
		String subject = "通过java，由A发送给B的消息";
		String content = "通过java，由A发送给B的消息"+new Date();
		String serverHost = "localhost";
		MailUtils.sendSame(from, to, subject, content, serverHost);
	}
	
	
	@Test
	public void user163ToQq(){
		String from = "ayinjun1109@163.com";
		String to = "1272097458@qq.com";
		String subject = "邮件发送";
		String content = "邮件发送"+new Date();
		String serverHost = "smtp.163.com";
		MailUtils.send(from, to, subject, content, serverHost);
	}
	
}
