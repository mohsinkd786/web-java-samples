<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
div {
	margin-top: 5px;
	padding-top: 5px;
	border: none;
}

div input[type="submit"] {
	border-radius: 6px;
	color: white;
	background-color: black;
	border: none;
}
</style>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<div>
		<%=request.getAttribute("message")%>
	</div>

	<%
		if (request.getAttribute("isLoggedIn") != null) {
	%>
	<div><%=request.getAttribute("isLoggedIn")%></div>
	<%
		}
	%>
	<div>
		<form method="POST" action="login">
			<div>
				<input type="text" name="username" placeholder="Username" />
			</div>
			<div>
				<input type="password" name="password" placeholder="Password" />
			</div>
			<div>
				<input type="submit" value="Login" />
			</div>
		</form>
	</div>
</body>
</html>