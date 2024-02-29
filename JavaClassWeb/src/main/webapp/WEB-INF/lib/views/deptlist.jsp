<%@page import="java.util.List"%>
<%@page import="kh.mclass.jdbc.model.vo.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
int sum(int a, int b){
	return a+b;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dept List</title>
</head>
<body>
	<br> =======
	<br>
	<%
	List<Dept> volist = (List<Dept>) request.getAttribute("deptlist"); //리턴 타입이 Object라면 다운캐스팅을 해준다
	if (volist != null) {
		if(volist.size() == 0){
			%>
	DEPT 데이터가 없습니다.
	<% 
		}else{
		for (Dept vo : volist) {
	%>
	<%=vo.getDeptno()%>|<%=vo.getDname()%>|<%=vo.getLoc()%>
	<br>
	<%		}
		}
	}else{
	%>
	잠시 시스템이 불안정하여 잠시후 다시 확인해주세요.
	<%
		
	}
	%>
	<h3>EL (Expression Language 표현언어로 표현만 가능/ for, if, 변수 선언등 제어문 없음)</h3>
	<br> ${deptlist.get(0).deptno } |${deptlist.get(0).dname }|${deptlist.get(0).loc }
	<br>
	<c:forEach begin="1" end="10" step="3" var="i">
	${i}
	<br>
	</c:forEach>

	<c:if test="${empty deptlist }">
	잠시 시스템이 불안정하여 잠시후 다시 확인해주세요.
	</c:if>

	<c:choose>
		<c:when test="${empty deptlist }">
		잠시 시스템이 불안정하여 잠시후 다시 확인해주세요.</c:when>
		<c:when test="${deptlist.size() == 0 }">
		잠시 시스템이 불안정하여 잠시후 다시 확인해주세요.</c:when>
		<c:otherwise>
			<c:forEach items="${deptlist}" var="vo" varStatus="vs">
			${vo.deptno} | ${vo.dname} | ${vo.dname} | ${vs.count } | ${vs.index }<br> 
			<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</body>
</html>