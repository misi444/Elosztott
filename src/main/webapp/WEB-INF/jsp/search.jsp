<%@include file="header.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Users</title>
	</head>
	
	<body>
		<a href="${useroriginationURL}">Add new user</a> <br />
		
		<h2>Registered users: </h2>
		<table>
			<thead>
				<tr>
					<th>USERNAME</th>
					<th>CREDIT</th>
					<th>SCHOOL</th>
					<th>FAVOURITE COLOURS</th>
					<th>GENDER</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.userName}</td>
						<td>${user.creditBalance}</td>
						<td>${user.qualification}</td>
						<td>
							<c:forEach items="${user.favouriteColor}" var="color">
								${color} <br/>
							</c:forEach>
						</td>
						<td>${user.gender}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>