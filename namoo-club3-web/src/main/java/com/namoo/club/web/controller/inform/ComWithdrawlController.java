package com.namoo.club.web.controller.inform;

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
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		
		req.setAttribute("name", name);
		req.setAttribute("comNo", comNo);
		
		service.removeCommunity(comNo);
		
		redirect(req, resp, "/community/comList.do");
		
	}

}
