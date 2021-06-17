package evoting.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import evoting.dao.RegistrationDao;
import evoting.entity.UserDetail;

/**
 * Servlet implementation class RegistrationCotrollerServlet
 */
@WebServlet("/RegistrationCotrollerServlet")
public class RegistrationCotrollerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationCotrollerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub	
		RequestDispatcher rd = null;
		UserDetail detail = new UserDetail();

		detail.setUserId(request.getParameter("userid"));
		detail.setAddress(request.getParameter("address"));
		detail.setCity(request.getParameter("city"));
		detail.setEmail(request.getParameter("email"));
		detail.setMobileNo(request.getParameter("mobile"));
		detail.setPassword(request.getParameter("password"));
		detail.setUsername(request.getParameter("username"));

		
		try {

			boolean result = false, userfound = false;
			if (!RegistrationDao.searchUser(detail.getUserId())) {
				if (RegistrationDao.registerUser(detail)) {
					result = true;
				}

			} else

				userfound = true;

			request.setAttribute("result", result);
			request.setAttribute("userfound", userfound);
			request.setAttribute("username", detail.getUsername());
			rd = request.getRequestDispatcher("registrationresponse.jsp");
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setAttribute("exception", ex);
			rd = request.getRequestDispatcher("showException.jsp");
		} finally {
				rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
