package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;


@WebServlet("/inform/clubWithdrawl.do")
@LoginRequired
public class ClubWithdrawlController extends DefaultController {

	private static final long serialVersionUID = -2515907141006105519L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		int clId = Integer.parseInt(req.getParameter("clId"));
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		
		req.setAttribute("name", name);
		req.setAttribute("clId", clId);
		req.setAttribute("cmId", cmId);
		
		service.removeClub(clId, cmId);
		
		redirect(req, resp, "/club/clubList.do?cmId=" + cmId);
	}
}
