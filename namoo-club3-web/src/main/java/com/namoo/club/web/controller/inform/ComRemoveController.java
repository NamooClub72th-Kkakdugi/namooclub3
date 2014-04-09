package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

@WebServlet("/inform/comRemove.do")
@LoginRequired
public class ComRemoveController extends DefaultController{

	private static final long serialVersionUID = 8129613231741023699L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		service.removeCommunity(comNo);
		req.setAttribute("name", req.getParameter("name"));
		
		redirect(req, resp, "/community/comList.do");
	}

}
