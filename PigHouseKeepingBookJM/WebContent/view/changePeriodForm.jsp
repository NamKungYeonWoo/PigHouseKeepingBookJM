<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<script type="text/javascript">
	function goMain() {
		location.href="mainPage.book";
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>�Ⱓ ����</h2>
<form action="changePeriod.book" method="post">
������: <input type="date" name="stDate" value=${stDate }> <br>
������: <input type="date" name="edDate" value=${edDate }><br><br>
<input type="submit" value="����">
<input type="button" value="���" onclick="goMain()">
</form>

</body>
</html>