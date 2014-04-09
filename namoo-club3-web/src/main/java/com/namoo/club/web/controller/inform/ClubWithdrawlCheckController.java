package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.Club;
import dom.entity.Community;

@WebServlet("/inform/clubWithdrawlCheck.do")
@LoginRequired
public class ClubWithdrawlCheckController extends DefaultController {

	private static final long serialVersionUID = -1227231654372102688L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		int clId = Integer.parseInt(req.getParameter("clId"));
		req.setAttribute("cmId", cmId);
		req.setAttribute("clId", clId);
		
		req.setAttribute("name", req.getParameter("name"));
		
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		Club club = service.findClub(clId);
		String clubName = club.getName();
		req.setAttribute("clubName", clubName);
		
		CommunityService service2 = NamooClubServiceFactory.getInstance().getCommunityService();
		Community community = service2.findCommunity(cmId);
		String comName = community.getName();
		req.setAttribute("ComName", comName);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inform/clubWithdrawlCheck.jsp");
		dispatcher.forward(req, resp);
	}
	
	

}
