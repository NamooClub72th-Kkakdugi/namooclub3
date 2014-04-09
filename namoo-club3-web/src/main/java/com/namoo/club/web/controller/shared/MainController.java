package com.namoo.club.web.controller.shared;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main.do")

public class MainController extends DefaultController {

	private static final long serialVersionUID = 8541086242638475900L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		System.out.println("MainController");
	}

}
