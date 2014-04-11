package com.namoo.club.web.controller.club;

import java.io.IOException;

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
import dom.entity.SocialPerson;

@WebServlet("/club/clubJoin.do")
@LoginRequired
public class ClubJoinController extends DefaultController {

	private static final long serialVersionUID = -4821770365482700212L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService comService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		int clubNo = Integer.parseInt(req.getParameter("clubNo"));
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		String name = person.getName();
		String email = person.getEmail();
		
		Community community = comService.findCommunity(comNo);
		Club club = service.findClub(clubNo);
		
		req.setAttribute("name", name);
		req.setAttribute("community", community);
		req.setAttribute("club", club);
		
		service.joinAsMember(clubNo, email);
		
		redirect(req, resp, "/club/clubList.do?name="+name+"&comNo="+comNo);
	}
}
