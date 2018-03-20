<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>IMC Service JSP</title>
		
	</head>
	
	<body>
	
		<jsp:useBean id="imcCalculator" class="negocio.Service" scope="page"/>
        <jsp:setProperty name="imcCalculator" property="*"/>
        <h1> IMC : <jsp:getProperty name="imcCalculator" property="IMC"/> </h1>

	</body>

</html>