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
	
	//������ ���̽��� ���� �޼ҵ�
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//Ŀ�ؼǿ��� ���ִ� �޼ҵ�
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getAllCount() {
		getCon();
		
		int count = 0;
		try {
			//���� �غ�
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			
			//���� ������ ����� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {	//�����Ͱ� �ִٸ�
				count = rs.getInt(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//ȭ�鿡 ������ �����͸� 10���� �����ؼ� �����ϴ� �޼ҵ�
	public Vector<BoardBean> getAllBoard(int startRow, int endRow){
		Vector<BoardBean> v = new Vector<>();
		getCon();
		try {
			//���� �ۼ�
			String sql = "select * from (select A.* , Rownum Rnum from (select * from board order by ref desc, re_level asc)A)"
					+ "where Rnum >= ? and Rnum <= ?";
			//���� ������ ��ü ����
			pstmt = con.prepareStatement(sql);
			//?�� ���� ����
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
	
	//�ϳ��� �Խñ��� �����ϴ� �޼ҵ� ȣ��
	public void insertBoard(BoardBean bean) {
		getCon();
		int ref = 0;
		int re_step = 1;
		int re_level = 1; 
		try {
			//���� �ۼ�
			String refsql = "select max(ref) from board";
			pstmt = con.prepareStatement(refsql);
			//���� ������ ����� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1)+1;	//���� ū���� 1�� ������
			}
			
			//�����͸� �����ϴ� ����
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
	
	//�ϳ��� �Խñ��� �о�帮�� �޼ҵ� �ۼ�
	public BoardBean getOneBoard(int num) {
		getCon();
		BoardBean bean = new BoardBean();
		
		try {
			//�ϳ��� �Խñ��� �о��ٴ� ��ȸ�� ����
			String countsql="update board set readcount = readcount + 1 where num = ?";
			
			pstmt = con.prepareStatement(countsql);
			pstmt.setInt(1, num);
			//���� ����
			pstmt.executeUpdate();
			
			//�ѰԽñۿ� ���� ������ �������ִ� ������ �ۼ�
			String sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//���� ���� �� ����� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {	//�ϳ��� �Խñ��� �����Ѵٸ�
				
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
	
	//������ �� ���� ��������
		public BoardBean UpdateBoardInfo(int num) {
			getCon();
			BoardBean bean = new BoardBean();
			
			try {
				
				//�ѰԽñۿ� ���� ������ �������ִ� ������ �ۼ�
				String sql = "select * from board where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//���� ���� �� ����� ����
				rs = pstmt.executeQuery();
				if(rs.next()) {	//�ϳ��� �Խñ��� �����Ѵٸ�
					
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
			//�ٸ� re_level�� ���� +1�� ����
			String levSql = "update board set re_level = re_level +1 where ref = ? and re_level > ?";
			pstmt = con.prepareStatement(levSql);
			pstmt.setInt(1, bean.getRef());
			pstmt.setInt(2, bean.getRe_level());
			pstmt.executeUpdate();
			
			//��� �Է�
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
	
	//�� ���� ����
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
	
	//�� ��й�ȣ ����
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
	
	//�� ����
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
	
	//�� ����
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
