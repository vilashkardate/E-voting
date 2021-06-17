package evoting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import evoting.dao.CandidateDAO;

/**
 * Servlet implementation class AddCandidateControllerServlet
 */
@WebServlet("/AddCandidateControllerServlet")
public class AddCandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCandidateControllerServlet() {
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
		RequestDispatcher rs = null;
		PrintWriter p = response.getWriter();
		try {
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid");
			if (userid == null) {
				session.invalidate();
				response.sendRedirect("accessdenied.html");
				return;
			}

			String canid = request.getParameter("id");
			String usid = request.getParameter("uid");
			if (canid != null && canid.equals("getid")) {
				String id = CandidateDAO.getNewId();
				p.println(id);
				return;
			}
			if (usid != null) {
				String username = CandidateDAO.getUserNameById(usid);
				ArrayList<String> cityList = CandidateDAO.getCity();

				// json
				JSONObject json = new JSONObject();
				StringBuilder sb = new StringBuilder();
				for (String c : cityList)
					sb.append("<option value='" + c + "'>" + c + "</option>");
				System.out.println(sb);
				System.out.println(username);
				if (username == null)
					username = "wrong";
				json.put("username", username);
				json.put("city", sb.toString());
				p.println(json);

			}

		} catch (Exception e) {
			e.printStackTrace();
			rs = request.getRequestDispatcher("showException.jsp");
			request.setAttribute("exception", e);
			rs.forward(request, response);
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
