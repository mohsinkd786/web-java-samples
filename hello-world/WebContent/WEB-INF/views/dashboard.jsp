<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ibm.java.beans.Employ"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>S.No</td>
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
</body>
</html>