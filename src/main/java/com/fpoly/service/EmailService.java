package com.fpoly.service;

import com.fpoly.entity.User;

import jakarta.servlet.ServletContext;

public interface EmailService {
	public void SendEmail(ServletContext context, User recipient, String type);
}
