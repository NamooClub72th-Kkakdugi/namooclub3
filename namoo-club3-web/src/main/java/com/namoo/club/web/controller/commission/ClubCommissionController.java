package com.namoo.club.web.controller.commission;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/commission/clubCommission.do")
@LoginRequired
public class ClubCommissionController extends DefaultController {

	private static final long serialVersionUID = 6263322653949147835L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		TownerService townService = NamooClubServiceFactory.getInstance().getTownerService();
		SocialPerson originPerson = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		String clId = req.getParameter("clId");
		String email = req.getParameter("email");
		String cmId = req.getParameter("cmId");
		
		SocialPerson person = townService.findTowner(email);
		service.commissionManagerCommunity(clId, person);
		
		req.setAttribute("cmId", cmId); 
		req.setAttribute("name", originPerson.getName());
		redirect(req, resp, "/club/clubList.do?cmId="+cmId);
	}

}
