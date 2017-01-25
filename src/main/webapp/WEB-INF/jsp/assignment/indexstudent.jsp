<%@ page import="org.apache.commons.io.FilenameUtils" %>
<%@include file="../header.jsp"%>
    <title>Uploaded assignments</title>
</head>
<body>
<a href="/admin/logout">Logout</a>
<h1>Uploaded assignments</h1>
<p><b>${error}</b></p>
<h2>${subject.getName()} (${subject.getSubjectId()})</h2>
<h4><a href="${studiedsubjectsURL}">Back to list</a></h4>
<h4><a href="${uploadassignmentURL}?subjectId=${subject.getSubjectId()}">Upload new</a></h4>
<br />
<table>
    <thead>
    <tr>
        <td>Filename</td>
        <td>Date</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${assignments}" var="assignment">
        <tr>
            <td>${FilenameUtils.getName(assignment.getPath())}</td>
            <td>${assignment.getDate()}</td>
            <td>
                <a href="${downloadassignmentURL}/${assignment.getId()}">Download</a>&nbsp;
                <a href="${deleteassignmentURL}/${assignment.getId()}/${assignment.getSubjectId()}" onclick="return confirm('Are you sure to delete the assignment?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>