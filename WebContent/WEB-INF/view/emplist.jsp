<%@	page import="com.neuedu.entity.Emp"%>
<%@	page import="java.util.List"%>
<%@	page import="java.text.SimpleDateFormat"%>
<%@	page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
	<head>
		<title>emplist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style2.css" />
		<style type="text/css">
			#headimg{
				border-radius:50%;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							${date}
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
							<img src="${user.headimg}" width="30px" height="30px" id="headimg">
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Name
							</td>
							<td>
								Salary
							</td>
							<td>
								Job
							</td>
							<td>
								Operation
							</td>
						</tr>
						<c:forEach items="${empList}" var="emp" varStatus="i">
							<c:if test="${i.index % 2 == 0}" var="isdan" scope="page">
								<tr class="row1">
									<td>
										${i.index+1}
									</td>
									<td>
										${emp.name}
									</td>
									<td>
										${emp.salary}
									</td>
									<td>
										${emp.job}
									</td>
									<td>
										<a href="deleteemp.do?id=${emp.id}">delete emp</a>&nbsp;<a href="updateempview.do?id=${emp.id}">update emp</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${!isdan}">
								<tr class="row2">
									<td>
										${i.index+1}
									</td>
									<td>
										${emp.name}
									</td>
									<td>
										${emp.salary}
									</td>
									<td>
										${emp.job}
									</td>
									<td>
										<a href="deleteemp.do?id=${emp.id}">delete emp</a>&nbsp;<a href="updateempview.do?id=${emp.id}">update emp</a>
									</td>
								</tr>
							</c:if>
					</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="Add Employee" onclick="location='insertempview.do'"/>
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
