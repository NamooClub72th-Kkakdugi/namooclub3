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
		String email = person.getEmail();
		int clubNo = Integer.parseInt(req.getParameter("clubNo"));
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		
		req.setAttribute("name", name);
		req.setAttribute("clubNo", clubNo);
		req.setAttribute("comNo", comNo);
		
		service.withdrawalClub(clubNo, email);
		
		redirect(req, resp, "/club/clubList.do?comNo=" + comNo);
	}
}
