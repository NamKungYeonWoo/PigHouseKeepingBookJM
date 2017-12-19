<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="mainHeader.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css); 
	*{
		font-family: 'Jeju Gothic', sans-serif;
		background-color: #f5faea;
		margin: 0px;
	}
	table {
	    border-collapse: collapse;
	    width: 100%;
	}
	
	td {
	    padding: 8px;s
	    text-align: left;
	    border-bottom: 1px solid LightGrey;
	}
	
	tr:hover{background-color:#f5f5f5}
	
	th {
	    padding: 8px;
	    text-align: left;
	    border-bottom: 1px double black;
	    background-color: LightGrey;
	}
	
	input {
		background-color: LightGrey;
	}
	
	body {
		margin:0px;
	}
	
</style>
<script type="text/javascript">
	function excelConvert() {
		location.href="excelConvertOutput.book"
	}
	function gomain() {
		location.href="mainPage.book"
	}
	function goInsert() {
		location.href="regOutputForm.book";
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2 align="center" style="margin-bottom: 70px">지출 상세보기</h2>
<h3 style="margin-bottom: 5px"> o지출 상세보기</h3>
<table border="1">
<tr><th>날짜</th><th>분류</th><th>내용</th><th>금액</th><th></th></tr>
<c:forEach items="${outputlist }" var="row">
	<tr>
		<td>${row.mdate } </td>
		<td>${row.cname } </td>
		<td>${row.content } </td>
		<td>${row.balance } </td>
		<td><a href="deleteDetail.book?aid=${row.aid }">삭제</a></td>
	</tr>
</c:forEach>
<tr style="border-top: 1px double black"><td colspan=3 style="text-align: center;">총&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;합</td><td>${ toutput}</td></tr>
</table>
<hr><br><br><br><br>
</body>
</html>