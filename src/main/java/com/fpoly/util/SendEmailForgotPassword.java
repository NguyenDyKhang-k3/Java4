package com.fpoly.util;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SendEmailForgotPassword {

	public static void SendMail(HttpServletRequest request, HttpServletResponse response, String email,
			HttpSession mySession) throws ServletException, IOException {

		email = request.getParameter("forgot-pass-email");

		int otpvalue = 0;
		mySession = request.getSession();

		if (email != null || email != "") {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(125565);

			String to = email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("khangndpc04859@fpt.edu.vn", "ukfewpnnsybotmzh");
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Xin chào !");
				message.setText("Mã OTP của bạn là: " + otpvalue);
				// send message
				Transport.send(message);
				System.out.println("send OTP success");
			}

			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			mySession.setAttribute("otp", otpvalue);
			mySession.setAttribute("email", email);
		}

	}

}
