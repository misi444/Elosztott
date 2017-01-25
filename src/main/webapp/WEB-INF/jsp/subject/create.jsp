    <%@include file="../header.jsp"%>
        <title>Create subject</title>
    </head>
    <body>
        <a href="/admin/logout">Logout</a>

        <c:forEach var="error" items="${errors}">
            <p><b>${error}</b></p>
        </c:forEach>

        <form:form modelAttribute="subject" action="${createsubjectURL}" method="POST" >
            <label>Subject id:</label>
            <form:input path="subjectId"></form:input> <br />

            <label>Subject name:</label>
            <form:input path="name"></form:input> <br />

            <label>Subject type:</label><br />
            <form:select path="type" items="${types}" multiple="false">
            </form:select><br />

            <form:hidden path="teacher"></form:hidden>
            <input type="submit" />
        </form:form>
    </body>
</html>