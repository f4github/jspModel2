package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//데이터 베이스에 연결 메소드
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//커넥션연결 해주는 메소드
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getAllCount() {
		getCon();
		
		int count = 0;
		try {
			//쿼리 준비
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			
			//쿼리 실행후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {	//데이터가 있다면
				count = rs.getInt(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//화면에 보여질 데이터를 10개씩 추출해서 리턴하는 메소드
	public Vector<BoardBean> getAllBoard(int startRow, int endRow){
		Vector<BoardBean> v = new Vector<>();
		getCon();
		try {
			//쿼리 작성
			String sql = "select * from (select A.* , Rownum Rnum from (select * from board order by ref desc, re_level asc)A)"
					+ "where Rnum >= ? and Rnum <= ?";
			//쿼리 실행할 객체 선언
			pstmt = con.prepareStatement(sql);
			//?에 값을 대입
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				v.add(bean);
			}
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//하나의 게시글을 저장하는 메소드 호출
	public void insertBoard(BoardBean bean) {
		getCon();
		int ref = 0;
		int re_step = 1;
		int re_level = 1; 
		try {
			//쿼리 작성
			String refsql = "select max(ref) from board";
			pstmt = con.prepareStatement(refsql);
			//쿼리 실행후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1)+1;	//가장 큰값에 1을 더해줌
			}
			
			//데이터를 삽입하는 쿼리
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5,ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//하나의 게시글을 읽어드리는 메소드 작성
	public BoardBean getOneBoard(int num) {
		getCon();
		BoardBean bean = new BoardBean();
		
		try {
			//하나의 게시글을 읽었다는 조회수 증가
			String countsql="update board set readcount = readcount + 1 where num = ?";
			
			pstmt = con.prepareStatement(countsql);
			pstmt.setInt(1, num);
			//쿼리 실행
			pstmt.executeUpdate();
			
			//한게시글에 대한 정보를 리턴해주는 쿼리를 작성
			String sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//쿼리 실행 후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {	//하나의 게시글이 존재한다면
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//수정용 글 정보 가져오기
		public BoardBean UpdateBoardInfo(int num) {
			getCon();
			BoardBean bean = new BoardBean();
			
			try {
				
				//한게시글에 대한 정보를 리턴해주는 쿼리를 작성
				String sql = "select * from board where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//쿼리 실행 후 결과를 리턴
				rs = pstmt.executeQuery();
				if(rs.next()) {	//하나의 게시글이 존재한다면
					
					bean.setNum(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setEmail(rs.getString(3));
					bean.setSubject(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setReg_date(rs.getDate(6).toString());
					bean.setRef(rs.getInt(7));
					bean.setRe_step(rs.getInt(8));
					bean.setRe_level(rs.getInt(9));
					bean.setReadcount(rs.getInt(10));
					bean.setContent(rs.getString(11));
					
				}
				con.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return bean;
		}
	
	public void reWrite(BoardBean bean) {
		getCon();
		int re_step = bean.getRe_step()+1;
		try {
			//다른 re_level들 전부 +1씩 해줌
			String levSql = "update board set re_level = re_level +1 where ref = ? and re_level > ?";
			pstmt = con.prepareStatement(levSql);
			pstmt.setInt(1, bean.getRef());
			pstmt.setInt(2, bean.getRe_level());
			pstmt.executeUpdate();
			
			//답글 입력
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5,bean.getRef());
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, bean.getRe_level()+1);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//글 제목 얻어옴
	public String getSubject(int num) {
		getCon();
		String subject = null;
		try {
			String sql = "select subject from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				subject = rs.getString(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subject;
	}
	
	//글 비밀번호 얻어옴
	public String getPassword(int num) {
		getCon();
		String password = null;
		try {
			String sql = "select password from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	//글 삭제
	public void deleteBoard(int num) {
		getCon();
		try {
			String sql = "delete from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//글 수정
	public void UpdateBoardInfo(BoardBean bean) {
		getCon();
		try {
			String sql = "update board set writer = ?, subject = ?, reg_date = sysdate, content = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getContent());
			pstmt.setInt(4, bean.getNum());
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
