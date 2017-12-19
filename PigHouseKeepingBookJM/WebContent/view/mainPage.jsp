<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="mainHeader_noMenu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css); 
	*{
		font-family: 'Jeju Gothic', sans-serif;
		background-color: #f5faea;

	}
	
	body {
		margin:0px;
	}
	
	h2{
		color: Indigo;
	}
	
	#chartdiv {
	  width: 100%;
	  height: 500px;
	}
    
    .bottom{
    	margin-bottom:100px;
    }
    
</style>
<script type="text/javascript">
	function changePeriod() {
		location.href="changePeriodForm.book";
	}
	
	function addOut() {
		location.href="regOutputForm.book";
	}
	
	function addIn() {
		location.href="regInputForm.book";
	}
	
	function logOut() {
		location.href="logout.book";
	}
</script>
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/pie.js"></script>
<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/none.js"></script>
<%-- <%= session.getAttribute("tinput"); 
%> --%>
<!-- Chart code -->

</head>
<body>
<br><br><br><br><br><br>
<div align="right" margin="5px">�Ⱓ: ${stDate } ~ ${edDate } &nbsp; <input type="button" value="����" onclick="changePeriod()"></div><br>
<div align="center">
<img src="./img/in.png" width="50px" height="50px">
<a href="inputDetail.book">�Ѽ���: ${tinput }��</a> <br>
<img src="./img/out.png" width="50px" height="50px">
<a href="outputDetail.book">������: ${toutput }��</a> <br><br><br>
����: ${tsub }�� <br><br><br>
<input type="button" value="���� �߰�" onclick="addIn()">
<input type="button" value="���� �߰�" onclick="addOut()">
<input type="button" value="�α׾ƿ�" onclick="logOut()"> <br>
<input type="hidden" id="tinput" value="${tinput }"> 
<input type="hidden" id="toutput" value="${toutput }">
<hr>
<br>�׷��� ���� <br>
<script>

	var pin = document.getElementById("tinput").value;
	var pout = document.getElementById("toutput").value;
	
	
	var chart = AmCharts.makeChart( "chartdiv", {
	  "type": "pie",
	  "theme": "none",
	  "dataProvider": [ {
	    "country": "����",
	    "litres": pin
	  }, {
	    "country": "����",
	    "litres": pout
	  } ],
	  "valueField": "litres",
	  "titleField": "country",
	   "balloon":{
	   "fixedPosition":true
	  },
	  "export": {
	    "enabled": true
	  }
	} );
</script>
<div id="chartdiv"></div>
</div>
</body>
</html>