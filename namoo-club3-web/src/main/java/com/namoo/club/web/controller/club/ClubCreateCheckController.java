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

import dom.entity.SocialPerson;


@WebServlet("/club/clubCreateCheck.do")
@LoginRequired
public class ClubCreateCheckController extends DefaultController{

	private static final long serialVersionUID = -6294726974519399084L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		String communityName = service.findCommunity(cmId).getName();
		String description = service.findCommunity(cmId).getDescription();
		
		String name = person.getName();
		String clubCategory = req.getParameter("clubCategory");
		String clubName = req.getParameter("clubName");
		String clubDescription = req.getParameter("clubDescription");
		
		req.setAttribute("name", name);
		req.setAttribute("cmId", cmId);
		req.setAttribute("clubCategory", clubCategory);
		req.setAttribute("clubName", clubName);
		req.setAttribute("clubDescription", clubDescription);
		req.setAttribute("communityName", communityName);
		req.setAttribute("description", description);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/clubCreateCheck.jsp");
		dispatcher.forward(req, resp);
	}
}
