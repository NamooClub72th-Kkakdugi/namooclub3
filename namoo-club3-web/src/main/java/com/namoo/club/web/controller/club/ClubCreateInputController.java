package com.namoo.club.web.controller.club;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.Community;
import dom.entity.SocialPerson;

@WebServlet("/club/clubCreateInput.do")
@LoginRequired
public class ClubCreateInputController extends DefaultController{

	private static final long serialVersionUID = -5423068621780728595L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		
		Community community = service.findCommunity(comNo);
		String name = person.getName();
		
		req.setAttribute("community", community);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/clubCreateInput.jsp");
		dispatcher.forward(req, resp);
	}
}
