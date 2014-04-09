package com.namoo.club.web.controller.community;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;
import com.namoo.club.web.shared.util.StringUtil;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.SocialPerson;

@WebServlet("/community/comCreate.do")
@LoginRequired
public class ComCreateController extends DefaultController {

	private static final long serialVersionUID = -769132475582817366L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		String name = person.getName();
		String email = person.getEmail();
		String communityName = req.getParameter("communityName");
		String description = req.getParameter("description");
		
		// 1. 커뮤니티 기본정보 및 카테고리 생성
		Community community = new Community(communityName, description, person);
		List<ClubCategory> categories = new ArrayList<>();
		int categoryNo = 1;
		for (int i = 1; i < 7; i++) {
			String categoryName = req.getParameter("ctgr" + i);
			if (!StringUtil.isEmpty(categoryName)) {
				ClubCategory category = new ClubCategory(community.getComNo(), categoryNo, categoryName);
				categories.add(category);
				categoryNo++;
			}
		}
		req.setAttribute("name", name);
		community = service.registCommunity(communityName, description, email, categories);
		
		redirect(req, resp, "/community/comList.do");
		
	}

}
