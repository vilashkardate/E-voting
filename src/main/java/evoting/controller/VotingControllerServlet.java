package evoting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDao;
import evoting.entity.CandidateInfo;

/**
 * Servlet implementation class VotingControllerServlet
 */
@WebServlet("/VotingControllerServlet")
public class VotingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VotingControllerServlet() {
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
		try {
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid");
			if (userid == null) {
				session.invalidate();
				response.sendRedirect("accessdenied.html");
				return;
			}
			
			String candID=VoteDao.getCandidateId(userid);
			if(candID==null)
			{
				List<CandidateInfo> candList= CandidateDAO.getCandidateInfoByCity(userid);
				request.setAttribute("candidateList", candList);
				rs = request.getRequestDispatcher("VotingProccess.jsp");		
			}else
			{
			CandidateInfo info=VoteDao.getVote(candID);
			request.setAttribute("info", info);
			rs = request.getRequestDispatcher("VotingAccessDenied.jsp");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("exception", exception);
			rs = request.getRequestDispatcher("showException.jsp");

		} finally {
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
