<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" 
    session="true"%>
<%@taglib 
	prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib 
	prefix="form" 
	uri="http://www.springframework.org/tags/form" %>
<%@ taglib 
	prefix="spring" 
	uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
		
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" >
		<link rel="stylesheet" href="<spring:theme code="styleSheet"></spring:theme>" type="text/css">

		<c:url var="useroriginationURL" value='/admin/userorigination'></c:url>
		<c:url var="createsubjectURL" value='/subject/create'></c:url>
		<c:url var="deletesubjectURL" value='/subject/delete'></c:url>
		<c:url var="enrolledstudentsURL" value='/subject/enrolledstudents'></c:url>
		<c:url var="taughtsubjectsURL" value='/subject/taughtsubjects'></c:url>
		<c:url var="studiedsubjectsURL" value='/subject/studiedsubjects'></c:url>
		<c:url var="enrollsubjectURL" value='/subject/enroll'></c:url>
		<c:url var="unenrollsubjectURL" value='/subject/unenroll'></c:url>
		<c:url var="assignmentsstudentURL" value='/assignment/indexstudent'></c:url>
		<c:url var="assignmentsteacherURL" value='/assignment/indexteacher'></c:url>
		<c:url var="uploadassignmentURL" value='/assignment/upload'></c:url>
		<c:url var="downloadassignmentURL" value='/assignment/download'></c:url>
		<c:url var="deleteassignmentURL" value='/assignment/delete'></c:url>
		
	<script type="text/javascript">
		$(document).ready(function() {
			$("#datatable").DataTable();
		});
	</script>