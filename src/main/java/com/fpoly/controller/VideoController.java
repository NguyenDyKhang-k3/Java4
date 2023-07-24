package com.fpoly.controller;

import java.io.IOException;

import com.fpoly.constant.SessionAtrr;
import com.fpoly.entity.History;
import com.fpoly.entity.User;
import com.fpoly.entity.Video;
import com.fpoly.service.HistoryService;
import com.fpoly.service.VideoService;
import com.fpoly.service.impl.HistoryServiceImpl;
import com.fpoly.service.impl.VideoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/video")
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionParam = request.getParameter("action");
		String href = request.getParameter("id");
		HttpSession session = request.getSession();

		switch (actionParam) {
		case "details": {
			doGetDetails(session, href, request, response);
			break;
		}
		case "like": {
			doGetLike(session, href, request, response);
			break;
		}
		case "watch": {
			doGetWatch(session, href, request, response);
			break;
		}
		}

	}

	protected void doGetDetails(HttpSession session, String href, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		request.setAttribute("video", video);

		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

		if (currentUser != null) {
			History history = historyService.create(currentUser, video);
			request.setAttribute("flagLikeButton", history.getIsLiked());
		}

		request.getRequestDispatcher("/views/user/video-detail.jsp").forward(request, response);
	}

	protected void doGetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnLike(currentUser, href);

		if (result == true) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}

	protected void doGetWatch(HttpSession session, String href, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		request.setAttribute("video", video);
		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

		if (currentUser != null) {
			History history = historyService.create(currentUser, video);
			request.setAttribute("flagLikeButton", history.getIsLiked());
		}
		request.getRequestDispatcher("/views/user/video-detail.jsp").forward(request, response);
	}

}
