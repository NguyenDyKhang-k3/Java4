package com.fpoly.controller.admin;

import java.io.IOException;
import java.util.List;

import com.fpoly.constant.SessionAtrr;
import com.fpoly.entity.User;
import com.fpoly.entity.Video;
import com.fpoly.service.VideoService;
import com.fpoly.service.impl.VideoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/admin/video" })
public class VideoControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VideoService videoService = new VideoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		// if (currentUser != null && currentUser.getIsAdmin()) {
		String action = request.getParameter("action");
		switch (action) {
		case "view":
			doGetOverView(request, response);
			break;
		case "delete":
			doGetDelete(request, response);
			break;
		case "add":
			request.setAttribute("isEdit", false);
			doGetAdd(request, response);
			break;
		case "edit":
			request.setAttribute("isEdit", true);
			doGetEdit(request, response);
			break;
		}
		// } else {
		// response.sendRedirect("index");
		// }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		// if (currentUser != null && currentUser.getIsAdmin()) {
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			doPostAdd(request, response);
			break;
		case "edit":
			doPostEdit(request, response);
			break;
		}
		// } else {
		// response.sendRedirect("index");
		// }
	}

	private void doGetOverView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/admin/video-overview.jsp").forward(request, response);
	}

	private void doGetDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String href = request.getParameter("href");
		Video videoDelete = videoService.delete(href);
		if (videoDelete != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}

//add
	private void doGetAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
	}

//edit
	private void doGetEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String href = request.getParameter("href");
		Video video = videoService.findByHref(href);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
	}

	private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");

		Video video = new Video();
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);
		Video videoReturn = videoService.create(video);
		if (videoReturn != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}

	private void doPostEdit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("newHref");
		String poster = req.getParameter("poster");
		String hrefOrigin = req.getParameter("hrefOrigin");

		Video video = videoService.findByHref(hrefOrigin);
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);

		Video videoReturn = videoService.update(video);
		if (videoReturn != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}

}
