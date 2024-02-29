<%@page import="kh.mclass.jdbc.model.vo.Salgrade"%>
<%@page import="kh.mclass.jdbc.model.vo.Emp"%>
<%@page import="kh.mclass.jdbc.model.vo.Dept"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br> =================
	<h3>dept list</h3>
	<%
	List<Dept> deptlist = (List<Dept>) request.getAttribute("deptlist"); //리턴 타입이 Object라면 다운캐스팅을 해준다

	for (Dept vo : deptlist) {
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
	<br> =================
	<h3>emp list</h3>
	<%
	List<Emp> emplist = (List<Emp>) request.getAttribute("emplist"); //리턴 타입이 Object라면 다운캐스팅을 해준다

	for (Emp vo : emplist) {
	%>
	<%=vo.getDeptno()%>
	|
	<%=vo.getEmpno()%>
	|
	<%=vo.getEname()%>
	|
	<%=vo.getJob()%>
	|
	<%=vo.getSal()%>
	<br>
	<%
	}
	%>
	<br> =================
	<h3>salgrade list</h3>
	<%
	List<Salgrade> sallist = (List<Salgrade>) request.getAttribute("sallist"); //리턴 타입이 Object라면 다운캐스팅을 해준다

	for (Salgrade vo : sallist) {
	%>
	<%=vo.getGrade()%>
	|
	<%=vo.getLosal()%>
	|
	<%=vo.getHisal()%>
	<br>
	<%
	}
	%>
</body>
</html>