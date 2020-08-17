package controll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

/**
 * Servlet implementation class ReWriteProc
 */
@WebServlet("/ReWriteProc.do")
public class ReWriteProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReWriteProc() {
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
		request.setCharacterEncoding("utf-8");
		
		BoardBean bean = new BoardBean();
		BoardDAO bdao = new BoardDAO();
		
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		bean.setRef(Integer.parseInt(request.getParameter("ref")));
		bean.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bean.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		bdao.reWrite(bean);
		
		response.sendRedirect("BoardListCon.do");
		
	}

}
