<%@include file="header.jsp"%>


		<title>User Statii</title>
	</head>
	
	<body>
		<a href="${useroriginationURL}">Add new user</a> <br />
		
		<h2>Registered users: </h2>
		<table id="datatable">
			<thead>
				<tr>
					<th><spring:message code="userName"/></th>
					<th><spring:message code="creditBalance"/></th>
					<th><spring:message code="qualification"/></th>
					<th><spring:message code="favouriteColor"/></th>
					<th><spring:message code="gender"/></th>
					<th><spring:message code="isRemove"/></th>
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
						<td><a href="/admin/search/<c:out value="${user.userId}"/>">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>