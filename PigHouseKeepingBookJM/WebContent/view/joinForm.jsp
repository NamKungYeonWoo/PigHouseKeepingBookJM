<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css); 
	*{
		font-family: 'Jeju Gothic', sans-serif;
		text-align: center;
		margin: 2px;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<h1>
<img src="./img/join.jpg" width="350px" height="140px">
</h1>
<form action="join.book" method="post">
	<div>
		���̵�: <input type="text" name="id"> <br>
		��й�ȣ: <input type="text" name="password"> <br>
		��й�ȣ Ȯ��: <input type="text" name="password2"> <br>
		����: <input type="text" name="name"> <br>
		���� �ּ�: <input type="text" name="email"><br>
	</div>
	<input type="submit" value="ȸ������"> <br> <br>
</form>
<a href="welcome.book">ó������</a>
</body>
</html>