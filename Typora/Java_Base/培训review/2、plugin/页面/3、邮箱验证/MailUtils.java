package com.igeek.shop.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
	// 收邮件的地址 to 主题 subject String text：邮件内容
	public static void send(final String from, String to, String subject, String content, String serverHost) {

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host",serverHost);
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true,主要针对第三方的客户端

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//相当于用授权码进行第三方登录邮箱
				return new PasswordAuthentication(from, "igeek123");//授权码
			}
		};

		Session session = Session.getInstance(props, auth);

		try {
			// 创建邮件
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			// 设置主题
			msg.setSubject(subject);
			// 设置时间
			msg.setSentDate(new Date());
			// 设置邮件的内容
			//msg.setText(content);
			//生成一个html格式的邮件内容
			msg.setContent(content, "text/html; charset=UTF-8");

			// 发送邮件
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}