package com.namoo.club.web.controller.commission;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.ClubMember;
import dom.entity.SocialPerson;

@WebServlet("/commission/clubSelectMem.xhtml")
@LoginRequired
public class ClubSelectMemController extends DefaultController{

	private static final long serialVersionUID = 7884665973587174715L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String clId = req.getParameter("clId");
		String clubName = service.findClub(clId).getName();
		List<ClubMember> members = service.findAllClubMember(clId);
		
		req.setAttribute("members", members);
		req.setAttribute("name", name);
		req.setAttribute("club", clubName);
		req.setAttribute("clId", clId);
		req.setAttribute("cmId", req.getParameter("cmId"));
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commission/clubSelectMem.jsp");
		dispatcher.forward(req, resp);
	}
}
