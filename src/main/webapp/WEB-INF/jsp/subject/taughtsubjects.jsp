<%@include file="../header.jsp"%>
    <title>Taught subjects</title>
</head>
<body>
<a href="/admin/logout">Logout</a>
<h1>Taught subjects</h1>
<p><b>${error}</b></p>
<h4><a href="create">Create subject</a></h4>
<br />
<table>
    <thead>
        <tr>
            <td>Subject id</td>
            <td>Subject name</td>
            <td>Subject type</td>
            <td>Actions</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${taughtSubjects}" var="subject">
            <tr>
                <td>${subject.getSubjectId()}</td>
                <td>${subject.getName()}</td>
                <td>${subject.getType()}</td>
                <td><a href="${enrolledstudentsURL}?subjectId=${subject.getSubjectId()}">Enrolled students</a></td>
                <td><a href="${assignmentsteacherURL}?subjectId=${subject.getSubjectId()}">Uploaded assignments</a></td>
                <td><a href="${deletesubjectURL}?id=${subject.getSubjectId()}" onclick="return confirm('Are you sure to delete the subject?')">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>