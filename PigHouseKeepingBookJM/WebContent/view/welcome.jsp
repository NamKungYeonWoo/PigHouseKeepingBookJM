<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css); 
	* {
		padding: 0px;
		margin: 0px;
		font-family: 'Jeju Gothic', sans-serif;
	}
	
	body {
		background-color: LightBlue;
	}
	
	.loginback {
		background-color: rgba(173, 216, 230, 0.7);
		padding: 10px;
		margin: 10px;
		height: 300px;
		width: 230px;
		align-items: center;
	}
	
	.loginbutton {
		background-color:  #63b4cf;
		padding: 10px;
		margin: 10px;
		width: 200px;
		border-color:  #63b4cf;
	}
	.grayText {
		color: DimGray;
		padding: 2px;
		margin: 2px;
		width: 200px;
		background-color: #bcd0d7;
	}
</style>
<script type="text/javascript">
	function goJoin() {
		location.href="joinForm.book"
	}
	
	function clearText(field) {
		if(field.defaultValue==field.value)
			   field.value="";
	}
</script>
</head>
<body>
<h1>
	<div width="100%" height="35px" style="padding: 10px; text-shadow: 2px 2px lightGrey; color: white;">돼지 가계부</div>
</h1>

<dir>
	<div style="position: absolute;">
		<div style="position: relative; top: 0px; left: 0px;" class="loginback">
		<center>
			<form action="login.book" method="post">
				<br>
				<img src="./img/loginPig.png" width="50px" height="50px"> <br><br>
				<input type="text" name="id" id="wid" value="아이디" onclick="clearText(this)" class="grayText"> <br>
				<input type="text" name="password" id="wpass" value="비밀번호" onclick="clearText(this)" class="grayText"> <br><br>
				<input type="submit" value="로그인" class="loginbutton"> <br>
				<input type="button" value="회원가입" onclick="goJoin()" class="loginbutton">
			</form>
		</center>
		</div>
	</div>
	<img src="./img/mainPage4.jpg" width="100%" height="350px">
</dir>
<hr> <br>
<p style="font-size: 8px; text-align: center;">pigMoneyDiary is your best friend in making your money history. We offers you lots of historic tools to make it easier for you to view your assets. <br>
About Us | Contact Us | Copyright Complaint
</p>
<br>

</body>
</html>