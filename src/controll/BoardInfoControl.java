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
 * Servlet implementation class BoardInfoControl
 */
@WebServlet("/BoardInfoControl.do")
public class BoardInfoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInfoControl() {
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
		//num
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//데이터베이스에 접근
		BoardDAO bdao = new BoardDAO();
		//하나의 게시글에 대한 정보를 리턴
		BoardBean bean = bdao.getOneBoard(num);
		
		request.setAttribute("bean", bean);
		
		//view -jsp 쪽으로 데이터를 넘겨줌
		RequestDispatcher dis = request.getRequestDispatcher("BoardInfo.jsp");
		dis.forward(request, response);
		
		
	}

}
