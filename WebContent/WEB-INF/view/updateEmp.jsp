<%@page import="com.neuedu.entity.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<title>update Emp</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="css/style2.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								2009/11/20
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">Main</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						update Emp info:
					</h1>
					<%
						Emp emp = (Emp)request.getAttribute("emp");
					%>
					<form action="updateemp.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									id:
								</td>
								<td valign="middle" align="left">
									<input type="text" name="id" value="<%=emp.getId()%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									Name:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" value="<%=emp.getName()%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									Salary:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="salary" value="<%=emp.getSalary()%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									Job:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="job" value="<%=emp.getJob()%>"/>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="Confirm" />
						</p>
					</form>
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
