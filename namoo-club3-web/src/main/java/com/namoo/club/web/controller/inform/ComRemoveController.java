package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;
import com.namoo.ns1.web.controller.shared.LoginRequired;

@WebServlet("/inform/comRemove.do")
@LoginRequired
public class ComRemoveController extends DefaultController{

	private static final long serialVersionUID = 8129613231741023699L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		String id = req.getParameter("id");
		service.removeCommunity(id);
		req.setAttribute("name", req.getParameter("name"));
		
		redirect(req, resp, "/community/comList.do");
	}

}
