package com.namoo.club.web.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/joinCheck.do")
public class joinCheckController extends HttpServlet {

	private static final long serialVersionUID = -2546297256571102347L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		req.setAttribute("name", name);
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/joinCheck.jsp");
		dispatcher.forward(req, resp);
	}
}
