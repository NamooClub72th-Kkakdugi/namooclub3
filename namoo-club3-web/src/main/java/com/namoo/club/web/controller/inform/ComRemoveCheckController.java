package com.namoo.club.web.controller.inform;

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


@WebServlet("/inform/comRemoveCheck.do")
@LoginRequired
public class ComRemoveCheckController extends DefaultController {

	private static final long serialVersionUID = -27287738010509105L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		req.setAttribute("comNo", comNo);
		Community community = service.findCommunity(comNo);
		String communityName = community.getName();
		req.setAttribute("communityName", communityName);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inform/comRemoveCheck.jsp");
		dispatcher.forward(req, resp);
	}

}
