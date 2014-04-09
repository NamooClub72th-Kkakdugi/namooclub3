package com.namoo.club.web.controller.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;


@WebServlet("/community/comJoinInput.xhtml")
@LoginRequired
public class ComJoinInputController extends DefaultController{

	private static final long serialVersionUID = 1089732649951719131L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String comNo = req.getParameter("comNo");
		req.setAttribute("comNo", comNo);
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/comJoinInput.jsp");
		dispatcher.forward(req, resp);
	}
}
