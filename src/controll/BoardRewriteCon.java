package controll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardRewriteCon
 */
@WebServlet("/BoardRewriteCon.do")
public class BoardRewriteCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRewriteCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
		
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		RequestDispatcher dis = request.getRequestDispatcher("ReWriteForm.jsp");
		dis.forward(request, response);
	}

}
