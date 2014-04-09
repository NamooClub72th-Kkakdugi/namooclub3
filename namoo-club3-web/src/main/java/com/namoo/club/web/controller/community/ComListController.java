package com.namoo.club.web.controller.community;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

import dom.entity.Community;
import dom.entity.SocialPerson;

@WebServlet("/community/comList.do")
@LoginRequired
public class ComListController extends DefaultController{

	private static final long serialVersionUID = 2015998138536728657L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String email = person.getEmail();
		String name = person.getName();
		
		List<Community> allCommunities = service.findAllCommunities();
		List<Community> joinCommunities = service.findBelongCommunities(email);
		List<Community> unjoinCommunities = filterList(allCommunities, joinCommunities);

		req.setAttribute("joinCommunities", joinCommunities);
		req.setAttribute("unjoincommunities", unjoinCommunities);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/comList.jsp");
		dispatcher.forward(req, resp);		
	}
	
	private List<Community> filterList(List<Community> allCommunities, List<Community> joinCommunities) {
		// 
		List<Community> unjoinCommunities = new ArrayList<Community>(allCommunities);
		List<Community> remove = new ArrayList<Community>();
		for (Community joinCommunity : joinCommunities) {
			for (Community community : allCommunities) {
				
				
				if (community.getId().equals(joinCommunity.getId())) {
					remove.add(community);
					break;
				}
			}
		}
		if (!remove.isEmpty()) {
			unjoinCommunities.removeAll(remove);
		}
		return unjoinCommunities;
	}
}
