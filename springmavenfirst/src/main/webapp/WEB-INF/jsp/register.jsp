<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="./login"> Go TO Login Page</a>
	</br>
	<form action="registerservlet" method="post">
		<pre>
Employee Name    :<input type="text" name="name" />
Employee Email   :<input type="text" name="email" />
Password         :<input type="text" name="password" />
Re Enter Password:<input type="text" name="repassword" />
Mobile Number    :<input type="text" name="mnumber" />
		<input type="submit" value="REGISTER">
</pre>
	</form>
	${msg}
</body>
</html>