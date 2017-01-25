<%@include file="../header.jsp"%>
    <title>Enrolled students</title>
</head>
<body>
<a href="/admin/logout">Logout</a>
<h1>Enrolled students</h1>
<p><b>${error}</b></p>
<h4><a href="taughtsubjects">Back to list</a></h4>
<br />
<table>
    <thead>
        <tr>
            <td>Student username</td>
            <td>Student name</td>
            <td>Actions</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${enrolledStudents}" var="student">
            <tr>
                <td>${student.getUserName()}</td>
                <td>${student.getName()}</td>
                <td><a href="${deletesubjectURL}?id=${student.getUserId()}" onclick="return confirm('Are you sure to delete the subject?')">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>