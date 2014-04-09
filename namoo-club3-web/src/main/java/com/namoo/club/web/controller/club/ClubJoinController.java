package com.namoo.club.web.controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/club/clubJoin.do")
@LoginRequired
public class ClubJoinController extends DefaultController {

	private static final long serialVersionUID = -4821770365482700212L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String clId =req.getParameter("clId") ;
		String cmId = req.getParameter("cmId");
		String name = person.getName();
		String email = person.getEmail();
		
		req.setAttribute("name", name);
		req.setAttribute("cmId", cmId);
		req.setAttribute("clId", clId);
		
		service.joinAsMember(clId, email);
		
		redirect(req, resp, "/club/clubList.do?name="+name+"&cmId="+cmId);
	}
}
