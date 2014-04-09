package com.namoo.club.web.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;


@WebServlet("/user/login.do")
public class LoginController extends DefaultController {

	private static final long serialVersionUID = -2532126402871325323L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		
		UserService service = NamooClubServiceFactory.getInstance().getUserService();
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
