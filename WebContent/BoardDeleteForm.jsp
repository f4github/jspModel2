<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form action = "BoardDeleteProc.do" method="post">
<table border="1">
	<tr>
		<td>������</td>
		<td>${subject }</td>
	</tr>
	<tr>
		<td>��й�ȣ �Է�</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Delete!">
		</td>
	</tr>
</table>
<input type="hidden" name = "num" value="${num }">
</form>
</body>
</html>