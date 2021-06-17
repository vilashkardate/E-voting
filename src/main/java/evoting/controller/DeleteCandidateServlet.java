package evoting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evoting.dao.CandidateDAO;
import evoting.entity.CandidateDetails;

/**
 * Servlet implementation class DeleteCandidateServlet
 */
@WebServlet("/DeleteCandidateServlet")
public class DeleteCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCandidateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        String data=request.getParameter("data");
        try
        {
        if(data!=null && data.equalsIgnoreCase("cid"))
        {
           ArrayList<String> candidateList=CandidateDAO.getAllCandidateId();
           request.setAttribute("candidateid",candidateList);
           request.setAttribute("result", "candidatelist");
        }
        else
        {
            CandidateDetails cd=CandidateDAO.getCandidateDetailByCanId(data);
            request.setAttribute("candidate",cd);
            request.setAttribute("result", "details");
        }
        rd=request.getRequestDispatcher("adminshowcandidate.jsp");
                
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            rd=request.getRequestDispatcher("showexception.jsp"); 
            request.setAttribute("Excpetion", ex);
        }
        finally
        {
            rd.forward(request, response);
        }

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
