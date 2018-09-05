<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ibm.java.beans.Employ"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.usr-details {
	font-size: 18px;
	font-weight: bold;
	text-align: left;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="usr-details">User Details</div>
	<div>
		<table>
			<tr>
				<td><b>S.No</b></td>
				<td><b>Name</b></td>
				<td><b>Email</b></td>
				<td><b>Salary</b></td>
			</tr>
			<%
				List<Employ> emps = (List<Employ>) request.getAttribute("emps");
				for (Employ emp : emps) {
			%>
			<tr>

				<td>
					<%
						out.println(emp.getId());
					%>
				</td>
				<td><%=emp.getName()%></td>
				<td><%=emp.getEmail()%></td>
				<td><%=emp.getSalary()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>