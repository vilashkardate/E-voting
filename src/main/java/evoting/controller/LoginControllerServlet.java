package evoting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import evoting.dao.UserDao;
import evoting.entity.UserLogin;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		RequestDispatcher rs=null;
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		UserLogin userLogin=new UserLogin(userid,password);
		try
		{
			String result=UserDao.ValidateUser(userLogin);
			request.setAttribute("result", result);
			request.setAttribute("userid",userid);
			rs=request.getRequestDispatcher("loginResponse.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			rs=request.getRequestDispatcher("showException.jsp");
		}
		finally {
			rs.forward(request, response);
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
