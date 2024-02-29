<%@page import="java.util.List"%>
<%@page import="kh.mclass.jdbc.modle.vo.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dept List</title>
</head>
<body>
	<br> ${DeptData1}
	<br> =======
	<%=request.getAttribute("DeptData1")%>
	<br> =======
	<br>
	<%
	List<Dept> volist = (List<Dept>) request.getAttribute("DeptData1"); //리턴 타입이 Object라면 다운캐스팅을 해준다

	for (Dept vo : volist) {
	%>
	<%=vo.getDeptno()%>
	|
	<%=vo.getDname()%>
	|
	<%=vo.getLoc()%>
	<br>
	<%
	}
	%>
	<h3>EL (Expression Language 표현언어로 표현만 가능/ for, if, 변수 선언등 제어문 없음)</h3>
	<br> ${DeptData1.get(0).deptno } |${DeptData1.get(0).dname }|${DeptData1.get(0).loc }
	<br>
</body>
</html>