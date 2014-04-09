package com.namoo.club.web.controller.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/community/comCreate.do")
@LoginRequired
public class ComCreateController extends DefaultController {

	private static final long serialVersionUID = -769132475582817366L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String email = person.getEmail();
		String communityName = req.getParameter("communityName");
		String description = req.getParameter("description");
		req.setAttribute("name", name);

		service.registCommunity(communityName, description, email);
		redirect(req, resp, "/community/comList.do");
		
	}

}
