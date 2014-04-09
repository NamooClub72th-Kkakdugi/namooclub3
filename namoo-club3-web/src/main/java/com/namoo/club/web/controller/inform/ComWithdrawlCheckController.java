package com.namoo.club.web.controller.inform;

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

import dom.entity.Community;
import dom.entity.SocialPerson;

@WebServlet("/inform/comWithdrawlCheck.do")
@LoginRequired
public class ComWithdrawlCheckController extends DefaultController {

	private static final long serialVersionUID = -1227231654372102688L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String id = req.getParameter("id");
		System.out.println(id);
	
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		Community community = service.findCommunity(id);
		String communityName = community.getName();
		req.setAttribute("communityName", communityName);
		
		req.setAttribute("id", id);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inform/comWithdrawlCheck.jsp");
		dispatcher.forward(req, resp);
	}
}
