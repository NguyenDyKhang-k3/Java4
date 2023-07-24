package com.fpoly.service.impl;

import com.fpoly.entity.User;
import com.fpoly.service.EmailService;
import com.fpoly.util.SendEmailUtil;

import jakarta.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {

	public static final String EMAIL_WELCOME_SUBJECT = "FILMVIET - CHÀO MỪNG BẠN ĐÃ THAM GIA BLOG XEM PHIM VIỆT";
	public static final String EMAIL_FORGOT_PASSWORD = "FILMVIET - ĐỔI MẬT KHẨU";

	@Override
	public void SendEmail(ServletContext context, User recipient, String type) {
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");

		try {
			String subject = "";
			String content = "";

			switch (type) {
			case "welcome":
				subject = EMAIL_WELCOME_SUBJECT;
				content = "Dear" + recipient.getUsername() + ", hope you have a good time";
				break;
			case "forgot":
				subject = EMAIL_FORGOT_PASSWORD;
				content = "Dear" + recipient.getUsername() + ", your new password here: " + recipient.getPassword();
				break;
			case "":
				subject = "Online Entertaiment";
				content = "Maybe this email is wrong, don't care about it";
				break;
			}
			SendEmailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception e) {
			System.out.println("Erorr EmailServiceImpl");
			e.printStackTrace();
		}
	}

}
