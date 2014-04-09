package com.namoo.club.web.controller.club;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.Club;
import dom.entity.Community;
import dom.entity.SocialPerson;

@WebServlet("/club/clubList.do")
@LoginRequired
public class ClubListController extends DefaultController {

	private static final long serialVersionUID = 8429308006701253816L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		CommunityService comService = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");

		String name = person.getName();
		String cmId = req.getParameter("cmId");
		String email = person.getEmail();

		Community community = comService.findCommunity(cmId);
		String communityName = community.getName();
		String description = community.getDescription();
		req.setAttribute("communityName", communityName);
		req.setAttribute("description", description);

		List<Club> allClubs = service.findAllClubs(cmId);
		List<Club> joinClubs = service.findBelongclubs(email, cmId);
		List<Club> unjoinClubs = filterList(allClubs, joinClubs);

		req.setAttribute("joinClubs", joinClubs);
		req.setAttribute("unJoinClubs", unjoinClubs);
		req.setAttribute("name", name);
		req.setAttribute("cmId", cmId);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/clubList.jsp");
		dispatcher.forward(req, resp);
	}

	private List<Club> filterList(List<Club> allClubs, List<Club> joinClubs) {
		//
		List<Club> unjoinClubs = new ArrayList<Club>(allClubs);
		List<Club> remove = new ArrayList<Club>();
		for (Club joinClub : joinClubs) {
			for (Club club : allClubs) {
				if (club.getId().equals(joinClub.getId())) {
					remove.add(club);
					break;
				}
			}
		}
		if (!remove.isEmpty()) {
			unjoinClubs.removeAll(remove);
		}
		return unjoinClubs;
	}
}