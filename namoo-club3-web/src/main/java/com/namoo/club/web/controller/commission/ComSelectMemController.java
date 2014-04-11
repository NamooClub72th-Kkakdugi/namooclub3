package com.namoo.club.web.controller.commission;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

@WebServlet("/commission/comSelectMem.xhtml")
@LoginRequired
public class ComSelectMemController extends DefaultController {

	private static final long serialVersionUID = 8194496863810223933L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		String communityName = service.findCommunity(comNo).getName();
		List<CommunityMember> members = service.findAllCommunityMember(comNo);
		
		CommunityManager manager = service.findCommunityManager(comNo);
		members = filter(members, manager);
		
		req.setAttribute("members", members);
		req.setAttribute("name", name);
		req.setAttribute("communityName", communityName);
		req.setAttribute("comNo", comNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commission/comSelectMem.jsp");
		dispatcher.forward(req, resp);
	}

	private List<CommunityMember> filter(List<CommunityMember> members, CommunityManager manager) {
		// 
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(manager.getEmail())) {
				found = member;
				System.out.println(found.getEmail());
				break;
			}
		}
		
		if (found != null) {
			members.remove(found);
		}
		
		return members;
	}
}
