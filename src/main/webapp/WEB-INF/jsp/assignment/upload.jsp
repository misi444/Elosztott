    <%@include file="../header.jsp"%>
        <title>Upload assignment</title>
    </head>
    <body>
    <a href="/admin/logout">Logout</a>
    <h1>Upload assignment</h1>
    <h2>${subject.getName()} (${subject.getSubjectId()})</h2>
    <h4><a href="${assignmentsstudentURL}?subjectId=${subject.getSubjectId()}">Back to list</a></h4>
    <br />

        <c:forEach var="error" items="${errors}">
            <p><b>${error}</b></p>
        </c:forEach>

        <form:form action="${uploadassignmentURL}" method="POST" enctype="multipart/form-data">
            <label>Please select a file to upload:</label>
            <br />
            <input path="file" type="file" name="file" />

            <br />

            <input type="hidden" name="subjectId" value="${subject.getSubjectId()}" />
            <input type="submit" />
        </form:form>
    </body>
</html>