<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Item Page</title>
</head>
<body>

	<form action="editItemServlet" method="post">
	Course:<input type="text" name="course" value="${itemToEdit.course}">
	Assignment:<input type="text" name="assignment" value="${itemToEdit.assignment}">
	<input type="hidden" name="id" value="${itemToEdit.id}">
	<input type="submit" value="Save Edited item">
	</form>

</body>
</html>