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
<title>Profile Details</title>
</head>
<body class = "standardLayout updateProfile">
<%@ include file="nav.jsp" %>

	<div class="container">
		<table class="table table-striped table-light table-hover">
		<thead class="thead-dark">
			<tr>
				<th colspan="2">Profile Details</th>
			</tr>
		</thead>
		<tbody id = "table" >
				<tr>
					<td colspan="2"><img src="${profileUpdated.pictureUrl}"/> </td>
				</tr>
				<tr>
					<td><span class = "headings">Profile ID: </span> </td>
					<td> <c:out value="${profileId}" /></td>
				</tr>
				<tr>
					<td> <span class = "headings">First Name: </span></td>
					<td> <c:out value="${profileUpdated.firstName}" /></td>
				</tr>
				<tr>
					<td>  <span class = "headings">Last Name:  </span></td>
					<td> <c:out value="${profileUpdated.lastName}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Age: </span></td>
					<td> <c:out value="${profileUpdated.age}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Gender: </span></td>
					<td><c:out value="${profileUpdated.gender}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Sexual Orientation: </span></td>
					<td><c:out value="${profileUpdated.sexualOrientation}" /></td>
				</tr>
				<tr>
					<td>  <span class = "headings">About me:  </span></td>
					<td><c:out value="${profileUpdated.aboutMe}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Picture URL:  </span></td>
					<td><c:out value="${profileUpdated.pictureUrl}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Min Age: </span></td>
					<td><c:out value="${profileUpdated.minAge}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Max Age: </span></td>
					<td><c:out value="${profileUpdated.maxAge}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">State:  </span></td>
					<td><c:out value="${profileUpdated.state}" /></td>
				</tr>
				<tr>
					<td>  <span class = "headings">City: </span></td>
					<td><c:out value="${profileUpdated.city}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Address Line 1:</span></td>
					<td><c:out value="${profileUpdated.address}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Address Line 2:</span></td>
					<td><c:out value="${profileUpdated.address2}" /></td>
				</tr>
				<tr>
					<td><span class = "headings">Zip Code: </span></td>
					<td><c:out value="${profileUpdated.zipCode}" /></td>
				</tr>
				<tr>
					<td> <span class = "headings">Interests: </span></td>
					<td>
     				<c:forEach items = "${profileUpdated.interests}" var = "interests">
						<li><c:out value = "${interests}"/></li>
					</c:forEach>	
         </td>
				</tr>
		</tbody>
	</table>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="js/main.js"></script>
</body>
</html>
