<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File Statistic Web App</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.css">
</head>
<body>

	<h1>Welcome to File Statistic Web App!</h1>
	<h2>Please select a file to handle:</h2>
	<form action="uploadNewFile" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" size="50" /> <br /> 
		<input class="btn btn-success" type="submit" value="Handle File" />
	</form>

	<div class="listOfFiles">
	    <h3>Handled files:</h3>
	    <form id="get_files" action="index" method="get">
	        <p><input class="btn btn-primary" type="submit" value="Refresh list" id="submit"></p>
	    </form>
	        <table class="table table-striped table-hover ">
	        	<thead>
					<tr>
						<th>Id</th>
						<th>Filename</th>
						<th>Longest word</th>
						<th>Shortest word</th>
						<th>Average word length</th>
					</tr>
				</thead>
				<c:forEach items="${files}" var="doc">
					<tr>
						<td>${doc.id}</td>
						<td><a href="linesStatistic?id=${doc.id}">${doc.filename}</a></td>
						<td>${doc.longestWordOfFile}</td>
						<td>${doc.shortestWordOfFile}</td>
						<td><fmt:formatNumber type="number" maxIntegerDigits="3" 
						value="${doc.averageWordLenghtOfFile}"/></td>
					</tr>
				</c:forEach>
			</table>
	    
	</div>
</body>
</html>