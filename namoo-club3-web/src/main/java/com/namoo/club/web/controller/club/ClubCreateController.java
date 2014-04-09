package com.namoo.club.web.controller.club;

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

@WebServlet("/club/clubCreate.do")
@LoginRequired
public class ClubCreateController extends DefaultController{

	private static final long serialVersionUID = 4827189201539721388L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		String email = person.getEmail();
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		String name = person.getName();
		
		String clubCategory = req.getParameter("clubCategory");
		String clubName = req.getParameter("clubName");
		String clubDescription = req.getParameter("clubDescription");
		
		req.setAttribute("name", name);
		req.setAttribute("cmId", cmId);
		
		service.registClub(clubCategory, cmId, clubName, clubDescription, email);
		
		redirect(req, resp, "/club/clubList.do?cmId="+cmId+"&name="+name);
	}
}
