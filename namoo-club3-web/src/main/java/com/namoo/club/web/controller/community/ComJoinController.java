package com.namoo.club.web.controller.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/community/comJoin.do")
@LoginRequired
public class ComJoinController extends DefaultController {

	private static final long serialVersionUID = -8664188246067720891L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();

		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		String email = person.getEmail();
		String name = person.getName();

		service.joinAsMember(comNo, email);
		req.setAttribute("name", name);
		redirect(req, resp, "/community/comList.do");

	}

}
