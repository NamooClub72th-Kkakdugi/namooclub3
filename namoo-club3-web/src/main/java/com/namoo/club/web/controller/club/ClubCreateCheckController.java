package com.namoo.club.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.web.controller.shared.DefaultController;
import com.namoo.club.web.controller.shared.LoginRequired;

import dom.entity.Club;
import dom.entity.SocialPerson;


@WebServlet("/club/clubCreateCheck.do")
@LoginRequired
public class ClubCreateCheckController extends DefaultController{

	private static final long serialVersionUID = -6294726974519399084L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		SocialPerson person = (SocialPerson) req.getSession().getAttribute("loginUser");
		
		String name = person.getName();
		System.out.println(name);
		int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
		System.out.println(categoryNo);
		String clubName = req.getParameter("clubName");
		System.out.println(clubName);
		String clubDescription = req.getParameter("clubDescription");
		System.out.println(clubDescription);
		int comNo = Integer.parseInt(req.getParameter("comNo"));
		System.out.println(comNo);
		String categoryName = req.getParameter("categoryName");
		Club club = new Club(categoryNo, categoryName , comNo, clubName, clubDescription, person);
		
		
		
		String clubQuOne = req.getParameter("clubQuOne");
		String clubQuTwo = req.getParameter("clubQuTwo");
		String clubQuThree = req.getParameter("clubQuThree");
		
		req.setAttribute("name", name);
		req.setAttribute("club", club);
		req.setAttribute("comNo", comNo);
		req.setAttribute("clubQuOne", clubQuOne);
		req.setAttribute("clubQuTwo", clubQuTwo);
		req.setAttribute("clubQuThree", clubQuThree);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/clubCreateCheck.jsp");
		dispatcher.forward(req, resp);
	}
}
