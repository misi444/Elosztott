<%@include file="../header.jsp"%>
    <title>Studied subjects</title>
</head>
<body>
<a href="/admin/logout">Logout</a>
<h1>Studied subjects</h1>
<p><b>${error}</b></p>
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
    <c:forEach items="${studiedSubjects}" var="subject">
        <tr>
            <td>${subject.getSubjectId()}</td>
            <td>${subject.getName()}</td>
            <td>${subject.getType()}</td>
            <td>
                <a href="${assignmentsstudentURL}?subjectId=${subject.getSubjectId()}">Assignments</a>
                <a href="${unenrollsubjectURL}?id=${subject.getSubjectId()}" onclick="return confirm('Are you sure to unenroll from the subject?')">Unenroll</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h1>Available subjects</h1>
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
    <c:forEach items="${availableSubjects}" var="subject">
        <tr>
            <td>${subject.getSubjectId()}</td>
            <td>${subject.getName()}</td>
            <td>${subject.getType()}</td>
            <td><a href="${enrollsubjectURL}?id=${subject.getSubjectId()}" onclick="return confirm('Are you sure to enroll to the subject?')">Enroll</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>