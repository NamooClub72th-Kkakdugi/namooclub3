package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.Club;
import dom.entity.Community;

@WebServlet("/inform/clubWithdrawlCheck.do")
@LoginRequired
public class ClubWithdrawlCheckController extends DefaultController {

	private static final long serialVersionUID = -1227231654372102688L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String cmId = req.getParameter("cmId");
		String clId = req.getParameter("clId");
		req.setAttribute("cmId", cmId);
		req.setAttribute("clId", clId);
		
//		System.out.println(cmId);
		
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
