
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<title>Event Details</title>
</head>
<body class="standardLayout">
<%@ include file="nav.jsp" %>
	<div class="container">
		<table class="table table-striped table-light table-hover">
		<thead class="thead-dark">
			<tr>
				<th colspan="2">Event Details</th>
			</tr>
		</thead>
		<tbody id = "table" >
				<tr>
					<td><span class = "headings">Event ID: </span> </td>
					<td> <c:out value="${event.id}" /></td>
				</tr>
				<tr>
					<td> <span class = "headings">Event Name: </span></td>
					<td> <c:out value="${event.name}" /></td>
				</tr>
				<tr>
					<td>  <span class = "headings">Description:  </span></td>
					<td> <c:out value="${event.description}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Date: </span></td>
					<td> <c:out value="${event.date}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Location: </span></td>
					<td><c:out value="${event.location.address}"/><br>
					<c:out value="${event.location.address2}"/><br>
					<c:out value="${event.location.city}"/><br>
					<c:out value="${event.location.state}"/><br>
					</td>
				</tr>
				<tr>
					<td><span class = "headings">Interests: </span></td>
					<td>
					<c:forEach var="interest" items="${event.interests}">
						<li>${interest.name}</li>
					</c:forEach></td>
				</tr>
		</tbody>
	</table>
		
		<form action="updateEvent.do?" method="GET">
			<input type="hidden" name="id" value="${event.id}" />
			<button type="submit" class="btn btn-light">Update</button>
		</form>
		<form action="deleteEvent.do?" method="GET">
			<input type="hidden" name="id" value="${event.id}" />
			<button type="submit" class="btn btn-light">Delete</button>
		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="js/main.js"></script>
</body>
</html>
