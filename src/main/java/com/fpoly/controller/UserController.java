package com.fpoly.controller;

import java.io.IOException;

import com.fpoly.constant.SessionAtrr;
import com.fpoly.entity.GooglePojo;
import com.fpoly.entity.User;
import com.fpoly.service.EmailService;
import com.fpoly.service.UserService;
import com.fpoly.service.impl.EmailServiceImpl;
import com.fpoly.service.impl.UserServiceImpl;
import com.fpoly.util.GoogleUtils;
import com.fpoly.util.RandomStringGenerator;
import com.fpoly.util.SendEmailForgotPassword;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/login", "/logout", "/register", "/forgot-pass", "/otp", "/newPassword", "/change-pass" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String path = request.getServletPath();
		switch (path) {
		case "/login":
			doGetLogin(request, response);
			break;
		case "/register":
			doGetRegister(request, response);
			break;
		case "/logout":
			doGetLogout(session, request, response);
			break;
		case "/forgot-pass":
			doGetForgotPass(request, response);
			break;
		case "/change-pass":
			doGetChangePass(request, response);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String path = request.getServletPath();

		switch (path) {
		case "/login":
			doPostLogin(session, request, response);
			break;
		case "/register":
			doPostRegister(session, request, response);
			break;
		case "/forgot-pass":
			doPostForgotPass(session, request, response);
			break;

		case "/otp":
			doPostOtp(request, response);
			break;
		case "/newPassword":
			doPostNewPassword(session, request, response);
			break;
		case "/change-pass":
			doPostChangePass(session, request, response);
			break;
		}
	}

//doGet
	// login
	private void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
		} else {
			String accessToken = GoogleUtils.getToken(code);
			GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

			String email = googlePojo.getEmail();
			String pass = RandomStringGenerator.generateRandomString(6);
			String username = RandomStringGenerator.generateUsername();

			User user = userService.findByEmail(email);
			if (user == null) {
				User addUserEmail = userService.create(email, pass, username);
				if (addUserEmail != null) {
					response.sendRedirect("index");
					session.setAttribute(SessionAtrr.CURRENT_USER, addUserEmail);
				}
			} else {
				response.sendRedirect("index");
				session.setAttribute(SessionAtrr.CURRENT_USER, user);

			}
		}
	}

	// register
	private void doGetRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
	}

	// forgot-pass
	protected void doGetForgotPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/forgot-pass.jsp").forward(request, response);
	}

	// logout
	private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.removeAttribute(SessionAtrr.CURRENT_USER);
		response.sendRedirect("index");
	}

	// Change Pass
	private void doGetChangePass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/changePass.jsp").forward(request, response);
	}

//doPost
	// login
	private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			session.setAttribute("loginSuccess", true);
			session.setAttribute(SessionAtrr.CURRENT_USER, user);
			response.sendRedirect("index");
		} else {
			session.setAttribute("loginSuccess", false);
			session.setAttribute("loginMessage", "Sai thông tin đăng nhập !");
			response.sendRedirect("login");
		}
	}

	// register
	private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("register-form-name");
		String password = request.getParameter("register-form-password");
		String email = request.getParameter("register-form-email");

		User existUser = userService.findByUsername(username);
		User existEmail = userService.findByEmail(email);

		if (existUser == null && existEmail == null) {
			User user = userService.register(username, password, email);

			if (user != null) {
				emailService.SendEmail(getServletContext(), user, "welcome");
				session.setAttribute("registerSuccess", true);
				// cho người dùng đăng nhập sẳn luôn
				session.setAttribute(SessionAtrr.CURRENT_USER, user);
				// trả về trang index
				response.sendRedirect("index");
			} else {
				// tiếp tục đăng ký lại
				response.sendRedirect("register");
			}
		} else {
			session.setAttribute("existUser", existUser != null);
			session.setAttribute("existEmail", existEmail != null);
			response.sendRedirect("register");
		}
	}

	// forgot pass
	protected void doPostForgotPass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("forgot-pass-email");

		User existEmail = userService.findByEmail(email);
		if (existEmail != null) {
			SendEmailForgotPassword.SendMail(request, response, email, session);
			request.getRequestDispatcher("/views/user/otp.jsp").forward(request, response);
		} else {
			session.setAttribute("existEmail", existEmail == null);
			response.sendRedirect("forgot-pass");
		}
	}

	// otp
	protected void doPostOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int value = Integer.parseInt(request.getParameter("otp"));
		HttpSession session = request.getSession();
		int otp = (int) session.getAttribute("otp");

		if (value == otp) {
			request.getRequestDispatcher("/views/user/newPassword.jsp").forward(request, response);
		} else {
			session.setAttribute("errorOTP", true);
			request.getRequestDispatcher("/views/user/otp.jsp").forward(request, response);
		}
	}

	// new Pass
	protected void doPostNewPassword(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String newPass = request.getParameter("newPass");
		String email = String.valueOf(session.getAttribute("email"));

		if (newPass != null && email != null) {
			User user = userService.resetPassword(email, newPass);

			if (user != null) {
				session.setAttribute("changePassSuccess", true);
				response.sendRedirect("login");
			}
		}
	}

	// change pass
	protected void doPostChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String oldPass = request.getParameter("Oldpass");
		String newPass = request.getParameter("newPass");
		String confirmPass = request.getParameter("confirmPass");

		User auth = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

		if (auth.getPassword() != null) {
			System.out.println(oldPass + " / " + auth.getPassword());
			if (auth.getPassword().equalsIgnoreCase(oldPass) && oldPass != null && !oldPass.isEmpty()) {
				if (confirmPass.equalsIgnoreCase(confirmPass)) {
					User user = userService.changePassword(auth.getUsername(), newPass);

					if (user != null) {
						session.removeAttribute(SessionAtrr.CURRENT_USER);
						request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
					} else {
						session.setAttribute("changePass", false);
						response.sendRedirect("change-pass");
						System.out.println("đăng nhập thất bại");
					}
				} else {
					session.setAttribute("changePass", false);
					response.sendRedirect("change-pass");
					System.out.println("mật khẩu mới với xác nhận mật khẩu không trùng nhau!");
				}
			} else {
				System.out.println("Mật khẩu của tài khoản " + auth.getUsername() + "Không đúng.");
				session.setAttribute("changePass", false);
				response.sendRedirect("change-pass");
			}
		} else {
			System.out.println("Vui lòng đăng nhập!");
			session.setAttribute("changePass", false);
			response.sendRedirect("change-pass");
		}

	}

}
