<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>답글</h2>

	<form action="ReWriteProc.do" method="post">
	<table>
		<tr>
			<td>Writer</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>Subject</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="email" name="email"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>Content</td>
			<td>
				<textarea rows="" cols=""></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="답글쓰기완료">
				<input type="reset" value="reset">
				<button type="button" onclick="location.href='BoardListCon.do'">전체 게시글 보기</button>
			</td>
		</tr>
	
	</table>
	<input type="hidden" name="num" value="${param.num }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="re_step" value="${param.re_step }">
	<input type="hidden" name="re_level" value="${param.re_level }">
	</form>
</body>
</html>