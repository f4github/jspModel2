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
<h2>게시글보기</h2>
	<table border="1">
		<tr>
			<td>글번호</td><td>${bean.num }</td><td>조회수</td><td>${bean.readcount } </td>
		</tr>
		<tr>
			<td>작성자</td><td>${bean.writer }</td><td>작성일</td><td>${bean.reg_date } </td>
		</tr>
		<tr>
			<td>이메일</td><td colspan="3">${bean.email}</td>
		</tr>
		<tr>
			<td>제목</td><td colspan="3">${bean.subject } </td>
		</tr>
		<tr>
			<td>글내용</td><td colspan="3">${bean.content } </td>
		</tr>
		<tr>
			<td colspan="4">
				<button onclick="location.href='BoardRewriteCon.do?num=${bean.num }&ref=${bean.ref }&re_step=${bean.re_step }&re_level=${bean.re_level }'">답글쓰기</button>
				<button onclick="location.href='BoardUpdateCon.do?num=${bean.num}'">수정하기</button>
				<button onclick="location.href='BoardDeleteCon.do?num=${bean.num }'">삭제하기</button>
				<button onclick="location.href='BoardList.jsp'">목록보기</button>
			</td>
		</tr>
	</table>

</body>
</html>