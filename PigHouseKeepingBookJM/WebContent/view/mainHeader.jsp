<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#fixed-menu {
	        width: 100%;
	        background-color: LightBlue;
	        position:fixed;
	        padding: 10px;
			margin: 0px;
	    }
	body {
		margin:0px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div id="fixed-menu" align="right">
<h2 align="center" style="background-color: LightBlue;">${mname }���� ���� ���� ����</h2>
	<input type="button" value="��������" onclick="excelConvert()">
	<input type="button" value="����Ʈ" onclick="window.print()">
	<input type="button" value="�����߰�" onclick="goInsert()">
	<input type="button" value="���ư���" onclick="gomain()">
	&nbsp; &nbsp;
</div>
</body>
</html>