<%@include file="header.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Become registered user</title>
	</head>
	
	<body>
		<c:forEach var="status" items="${statii}">
			<p><b>${status}</b></p>
		</c:forEach>
		
		<form:form modelAttribute="userRequest" action="${useroriginationURL}" method="POST" >
			<label>User name:</label>
			<form:input path="userName"></form:input> <br />
			
			<label>Credit:</label>
			<form:input path="creditBalance"></form:input> <br />
		
			<label>Education level:</label><br />
		
			<form:select path="qualification" items="${qualification}" multiple="false">
			</form:select><br />
		
			<label>Favorite colors:</label> <br />
			<form:checkboxes path="favouriteColor" items="${colors}" /> <br />
			
			<label>Gender:</label> <br />
			<form:radiobuttons path="gender" items="${genders}"/>

			<input type="submit">
		</form:form>
	</body>
</html>