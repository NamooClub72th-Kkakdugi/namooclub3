package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

@WebServlet("/inform/clubRemove.do")
@LoginRequired
public class ClubRemoveController extends DefaultController{

	private static final long serialVersionUID = -3829572762564286577L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService comService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		String name = req.getParameter("name");
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		int clId = Integer.parseInt(req.getParameter("clId"));
		
		comService.findCommunity(cmId).removeClub(clId);
		service.removeClub(clId, cmId);
		
		req.setAttribute("cmId", cmId);
		req.setAttribute("name", name);
		
		redirect(req, resp, "/club/clubList.do?cmId="+cmId);
	}

}
