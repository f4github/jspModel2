<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
aaaa ${bean.re_level } aaa
<h2>�Խñۺ���</h2>
	<table border="1">
		<tr>
			<td>�۹�ȣ</td><td>${bean.num }</td><td>��ȸ��</td><td>${bean.readcount } </td>
		</tr>
		<tr>
			<td>�ۼ���</td><td>${bean.writer }</td><td>�ۼ���</td><td>${bean.reg_date } </td>
		</tr>
		<tr>
			<td>�̸���</td><td colspan="3">${bean.email}</td>
		</tr>
		<tr>
			<td>����</td><td colspan="3">${bean.subject } </td>
		</tr>
		<tr>
			<td>�۳���</td><td colspan="3">${bean.content } </td>
		</tr>
		<tr>
			<td colspan="4">
				<button onclick="location.href='BoardRewriteCon.do?num=${bean.num }&ref=${bean.ref }&re_step=${bean.re_step }&re_level=${bean.re_level }'">��۾���</button>
				<button onclick="location.href='BoardUpdateCon.do?num=${bean.num}'">�����ϱ�</button>
				<button onclick="location.href='BoardDeleteCon.do?num=${bean.num }'">�����ϱ�</button>
				<button onclick="location.href='BoardList.jsp'">��Ϻ���</button>
			</td>
		</tr>
	</table>

</body>
</html>