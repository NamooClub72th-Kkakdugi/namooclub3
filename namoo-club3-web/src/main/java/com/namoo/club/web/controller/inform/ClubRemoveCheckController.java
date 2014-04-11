package com.namoo.club.web.controller.inform;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;



@WebServlet("/inform/clubRemoveCheck.do")
@LoginRequired
public class ClubRemoveCheckController extends DefaultController {

	private static final long serialVersionUID = -5716529646596314036L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		int clubNo = Integer.parseInt(req.getParameter("clubNo"));
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		
		String name = req.getParameter("name");
		String clubName = service.findClub(clubNo).getName();
		req.setAttribute("clubName", clubName);
		req.setAttribute("clubNo", clubNo);
		req.setAttribute("name", name);
		req.setAttribute("comNo", comNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inform/clubRemoveCheck.jsp");
		dispatcher.forward(req, resp);
	}

}
