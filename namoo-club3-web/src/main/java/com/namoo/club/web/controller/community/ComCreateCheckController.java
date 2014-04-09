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

@WebServlet("/community/comCreateCheck.do")
@LoginRequired
public class ComCreateCheckController extends DefaultController{

	private static final long serialVersionUID = 6748757820209095647L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		SocialPerson person = (SocialPerson)req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String communityName = req.getParameter("communityName");
		String description = req.getParameter("description");
		req.setAttribute("name", name);
		req.setAttribute("communityName", communityName);
		req.setAttribute("description", description);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/comCreateCheck.jsp");
		dispatcher.forward(req, resp);
	}

}
