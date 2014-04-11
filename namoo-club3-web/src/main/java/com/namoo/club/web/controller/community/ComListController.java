package com.namoo.club.web.controller.community;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.community.pres.PresCommunity;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

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
		req.setAttribute("name", name);
		
		List<Community> allCommunities = service.findAllCommunities();
		List<Community> joinCommunities = service.findBelongCommunities(email);
		List<Community> unjoinCommunities = filterList(allCommunities, joinCommunities);

		List<PresCommunity> presJoinedCommunities = convertAll(joinCommunities, service, email);
		List<PresCommunity> presUnjoinedCommunities = convertAll(unjoinCommunities, service, email);
	
		
		req.setAttribute("joinCommunities", presJoinedCommunities);
		req.setAttribute("unjoincommunities", presUnjoinedCommunities);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/comList.jsp");
		dispatcher.forward(req, resp);		
	}

	private List<PresCommunity> convertAll(List<Community> communities, CommunityService service, String loginEmail) {
		// 
		ClubService clubservice = NamooClubServiceFactory.getInstance().getClubService();
		List<PresCommunity> presCommunities = new ArrayList<PresCommunity>();
		for (Community community : communities) {
			
			int communityNo = community.getComNo();
			PresCommunity presCommunity = new PresCommunity(community);
			presCommunity.setManager(service.findCommunityManager(communityNo));
			presCommunity.setMembers(service.findAllCommunityMember(communityNo));
			presCommunity.setClubs(clubservice.findAllClubs(communityNo));
			presCommunity.setLoginEmail(loginEmail);
			
			presCommunities.add(presCommunity);
		}
		return presCommunities;
	}

	private List<Community> filterList(List<Community> allCommunities, List<Community> joinCommunities) {
		// 
		List<Community> unjoinCommunities = new ArrayList<Community>(allCommunities);
		List<Community> remove = new ArrayList<Community>();
		for (Community joinCommunity : joinCommunities) {
			for (Community community : allCommunities) {
				if (community.getComNo() == (joinCommunity.getComNo())) {
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
