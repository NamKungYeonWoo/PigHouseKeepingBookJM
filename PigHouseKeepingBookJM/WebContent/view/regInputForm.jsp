<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css); 
	*{
		font-family: 'Jeju Gothic', sans-serif;
		background-color: #f5faea;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>���� ���</h1> <br>
<form action="regInput.book" method="post">
	��¥: <input type="date" name="mdate"> <br>
	����: <input type="text" name="content"> <br>
	�ݾ�: <input type="text" name="balance"> <br>
	�з�: 
	<select name="cate">
		<c:forEach items="${ic_list }" var="row">
			<option value="${row.code }">${row.name}</option>
		</c:forEach>
	</select> <br> <br>
	<input type="submit" value="���">
	<input type="reset" value="�ʱ�ȭ">
</form>
<a href="mainPage.book">ó������</a>
</body>
</html>