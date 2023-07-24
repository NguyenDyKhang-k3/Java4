package com.fpoly.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.constant.SessionAtrr;
import com.fpoly.dto.UserDto;
import com.fpoly.dto.VideoLikedInfo;
import com.fpoly.entity.User;
import com.fpoly.service.StatsService;
import com.fpoly.service.UserService;
import com.fpoly.service.VideoService;
import com.fpoly.service.impl.StatsServiceImpl;
import com.fpoly.service.impl.UserServiceImpl;
import com.fpoly.service.impl.VideoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/admin", "/admin/favorites", "/admin/history" })
public class HomeControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final int VIDEO_MAX_PAGE_SIZE = 10;
	private VideoService videoService = new VideoServiceImpl();
	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {

			String path = req.getServletPath();

			switch (path) {
			case "/admin":
				doGetHome(req, resp);
				break;
			case "/admin/favorites":
				doGetFavorites(req, resp);
				break;
			case "/admin/history":
				doGetHistory(req, resp);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}

		} else {
			resp.sendRedirect("index");
		}

	}

	private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}

	private void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if (users.isEmpty()) {
			resp.setStatus(400);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String dataResponse = mapper.writeValueAsString(users);
			resp.setStatus(200);
			out.print(dataResponse);
			out.flush();
		}
	}

	private void doGetHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}

}
