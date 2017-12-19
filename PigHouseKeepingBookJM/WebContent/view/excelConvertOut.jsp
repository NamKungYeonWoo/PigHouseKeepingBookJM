<%@ page language="java" contentType="application/vnd.ms-excel;charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	response.setHeader("Content-Disposition","attachment;filename=output.xls");
	response.setHeader("Content-Description", "JSP Generated Data");
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here2</title>
</head>
<body>
<table border="1">
<tr><th>날짜</th><th>분류</th><th>내용</th><th>금액</th></tr>
<c:forEach items="${outputlist }" var="row">
	<tr>
		<td>${row.mdate } </td>
		<td>${row.cname } </td>
		<td>${row.content } </td>
		<td>${row.balance } </td>
		
	</tr>
</c:forEach>
</table>
</body>
</html>