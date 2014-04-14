package com.namoo.club.web.controller.commission;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/commission/clubCommission.do")
@LoginRequired
public class ClubCommissionController extends DefaultController {

	private static final long serialVersionUID = 6263322653949147835L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		UserService userService = NamooClubServiceFactory.getInstance().getUserService();
		SocialPerson originPerson = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		int clubNo = Integer.parseInt(req.getParameter("clubNo"));
		String email = req.getParameter("email");
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		
		SocialPerson person = userService.findTowner(email);
		service.commissionManagerClub(clubNo, originPerson, person);
		
		req.setAttribute("comNo", comNo); 
		req.setAttribute("name", originPerson.getName());
		redirect(req, resp, "/club/clubList.do?comNo="+comNo);
	}

}
