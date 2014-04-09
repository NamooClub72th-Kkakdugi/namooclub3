package com.namoo.club.web.controller.commission;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/commission/comCommission.do")
@LoginRequired
public class ComCommissionController extends DefaultController{

	private static final long serialVersionUID = -9106880770468221419L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		UserService townService = NamooClubServiceFactory.getInstance().getUserService();
		SocialPerson originPerson = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		String email = req.getParameter("email");
		
		SocialPerson person = townService.findTowner(email);
		service.commissionManagerCommunity(comNo, person);
		 
		req.setAttribute("name", originPerson.getName());
		redirect(req, resp, "/community/comList.do");
	}

}
