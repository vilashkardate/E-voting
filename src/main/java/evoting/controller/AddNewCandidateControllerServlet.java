package evoting.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import evoting.dao.CandidateDAO;
import evoting.entity.CandidateEntity;

/**
 * Servlet implementation class AddNewCandidateControllerServlet
 */
@WebServlet("/AddNewCandidateControllerServlet")
public class AddNewCandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCandidateControllerServlet() {
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
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			ServletRequestContext src = new ServletRequestContext(request);
				List<FileItem> mlist = sfu.parseRequest(src);
			ArrayList<String> detail = new ArrayList<String>();
			InputStream is=null;
			for (FileItem fit : mlist) {
				if (fit.isFormField()) {
					String fname = fit.getFieldName();
					String value = fit.getString();
					System.out.println("inside if \n" + fname + " : " + value);
					detail.add(value);
				} else {
					is=fit.getInputStream();
					String key = fit.getFieldName();
					String filename = fit.getName();
					System.out.println("inside else \n" + key + " : " + filename);
				}
			}
			
			CandidateEntity entity=new CandidateEntity(detail.get(0),detail.get(3),detail.get(4),detail.get(1),is);
				boolean result=CandidateDAO.addCandidate(entity);
				if(result)
					rs=request.getRequestDispatcher("success.jsp");
				else
					rs=request.getRequestDispatcher("error.jsp");
			
		} catch (Exception e) {
			System.out.println("Exception in add candidate ");
			e.printStackTrace();
		}
		finally {
			if(rs!=null)
			{
				rs.forward(request, response);
			}
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
