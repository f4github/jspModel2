<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${differentType == 1 }">
		<script>
			alert('비밀번호가 틀렸어라~');
			history.go(-1);
		</script>
	</c:when>
</c:choose>

</body>
</html>