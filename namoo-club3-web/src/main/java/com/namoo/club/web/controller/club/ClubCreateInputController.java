package com.namoo.club.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

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
		String cmId = req.getParameter("cmId");
		String communityName = service.findCommunity(cmId).getName();
		String description = service.findCommunity(cmId).getDescription();
		String name = person.getName();
		
		req.setAttribute("communityName", communityName);
		req.setAttribute("description", description);
		req.setAttribute("cmId", cmId);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/clubCreateInput.jsp");
		dispatcher.forward(req, resp);
	}
}
