package com.namoo.club.web.controller.inform;

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

@WebServlet("/inform/comWithdrawl.do")
@LoginRequired
public class ComWithdrawlController extends DefaultController{

	private static final long serialVersionUID = 8590045166685482237L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String id = req.getParameter("id");
		
		req.setAttribute("name", name);
		req.setAttribute("id", id);
		
		service.removeCommunity(id);
		
		redirect(req, resp, "/community/comList.do");
		
	}

}
