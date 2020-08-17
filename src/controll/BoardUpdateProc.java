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
 * Servlet implementation class BoardUpdateProc
 */
@WebServlet("/BoardUpdateProc.do")
public class BoardUpdateProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateProc() {
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
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdao = new BoardDAO();
		
		
		BoardBean bean = new BoardBean();
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		bean.setWriter(request.getParameter("writer"));
		bean.setEmail(request.getParameter("email"));
		bean.setSubject(request.getParameter("subject"));
		bean.setContent(request.getParameter("content"));
		
		String inputPass = request.getParameter("password");
		String password = bdao.getPassword(num);
		
		if(inputPass.equals(password)) {
			bdao.UpdateBoardInfo(bean);
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request, response);
		}else {
			int differentType = 1;
			request.setAttribute("differentType", differentType);
			RequestDispatcher dis = request.getRequestDispatcher("Different.jsp");
			dis.forward(request, response);
		}
			
		
	}

}
