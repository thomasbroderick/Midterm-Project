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
<title>User Details</title>
</head>
<body class = "standardLayout">
<%@ include file="nav.jsp" %>

	<div class="container displayInformation">
	<c:forEach items="${threadMessages}" var="m">
	<table>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Sender</th>
				<th scope="col">Date</th>
			</tr>
		</thead>
		<tbody id = "table" >
				<tr>
					<td><c:out value="${m.sender.firstName}" /></td>
					<td><c:out value="${m.dateSent}" /></td>
				</tr>
				<tr>
					<td colspan="2"><img src="<c:out value="${m.messageText}" />"></td>
				</tr>
		</tbody>
		</table>
		</c:forEach>
		
		<form action="replyMessage.do" method="POST" >
		Message<input type="text" name="messageText" placeholder="Enter a message" style="width:470px; height:100px;">
		<c:if test="${threadMessages[0].sender.id == profile.id }">
		<input type="hidden" name="matchProfile" value="${threadMessages[0].recipient}"/>
		</c:if>
		<c:if test="${threadMessages[0].recipient.id == profile.id }">
		<input type="hidden" name="matchProfile" value="${threadMessages[0].sender}"/>
		</c:if>
		<input type="hidden" name="threadId" value="${threadMessages[0].threadId}"/>
		</form>
		
		</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="js/main.js"></script>
</body>
</html>