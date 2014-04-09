package com.namoo.club.web.controller.commission;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/commission/comCommission.do")
@LoginRequired
public class ComCommissionController extends DefaultController{

	private static final long serialVersionUID = -9106880770468221419L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		TownerService townService = NamooClubServiceFactory.getInstance().getTownerService();
		SocialPerson originPerson = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		String cmId = req.getParameter("cmId");
		String email = req.getParameter("email");
		
		SocialPerson person = townService.findTowner(email);
		service.commissionManagerCommunity(cmId, person);
		 
		req.setAttribute("name", originPerson.getName());
		redirect(req, resp, "/community/comList.do");
	}

}
