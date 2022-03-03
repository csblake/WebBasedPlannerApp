<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Planner Lists</title>
</head>
<body>

	<form method="post" action="listnavigationServlet">
	<table>
	<c:forEach items="${requestScope.allPlanners}" var="currentplanner">
	<tr>
		<td><input type="radio" name="id" value="${currentplanner.id}"></td>
		<td><h2>${currentplanner.plannerName}</h2></td></tr>
		<tr><td colspan="3">Planner Date: ${currentplanner.plannerDate}</td></tr>
		<tr><td colspan="3">Student: ${currentplanner.student.studentName}</td></tr>
		<c:forEach var="plannerVal" items="${currentplanner.listOfPlanners}">
		<tr><td></td><td colspan="3">
			${plannerVal.course}, ${plannerVal.assignment}
			</td>
		</tr>
		</c:forEach>
	</c:forEach>
	</table>
	<input type="submit" value="edit" name="doThisToPlanner">
	<input type="submit" value="delete" name="doThisToPlanner">
	<input type="submit" value="add" name="doThisToPlanner">
	</form>
	<a href="addItemsForPlannerServlet">Create a new Planner</a>
	<a href="index.html">Insert a new Assignment</a>

</body>
</html>