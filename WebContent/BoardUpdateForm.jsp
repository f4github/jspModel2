<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Update Form</h2>
	<form action="BoardUpdateProc.do" method = "post" name="bean">
	<table border="1">
		<tr>
			<td>글번호</td><td>${bean.num }</td><td>조회수</td><td>${bean.readcount }</td>
		</tr>
		<tr>
			<td>작성자</td><td><input type="text" name="writer" value ="${bean.writer}"></td><td>작성일</td><td>${bean.reg_date }</td>
		</tr>
		<tr>
			<td>이메일</td><td colspan="3"><input type="eamil" name="email" value="${bean.email }"></td>
		</tr>
		<tr>
			<td>제목</td><td colspan="3"><input type="text" name="subject" value="${bean.subject }" ></td>
		</tr>
		<tr>
			<td>글내용</td><td colspan="3"><textarea name = "content">${bean.content }</textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="submit">Update</button>
				<button type="button" onclick="location.href='BoardListCon.do'">목록보기</button>
			</td>
		</tr>
	</table>
	<input type="hidden" name="num" value="${bean.num }">
	<input type="hidden" name="ref" value="${bean.ref }">
	<input type="hidden" name="re_step" value="${bean.re_step }">
	<input type="hidden" name="re_level" value="${bean.re_level }">
	<input type="hidden" name="readCount" value="${bean.readcount }">
	</form>



</body>
</html>