package com.namoo.club.web.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.shared.DefaultController;

@WebServlet("/user/login.do")
public class LoginController extends DefaultController {

	private static final long serialVersionUID = -2532126402871325323L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		
		TownerService service = NamooClubServiceFactory.getInstance().getTownerService();
		boolean login = service.loginAsTowner(loginId, password);
		
		if (login) {
			redirect(req, resp, "/community/comList.do");
			req.getSession().setAttribute("loginUser", service.findTowner(loginId));
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inform/loginError.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
