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

	<div class="lines">
		<h3>File: ${file.filename}</h3>
		<form id="back" action="index" method="get">
	        <p><input class="btn btn-primary" type="submit" value="Back to file list" id="submit"></p>
	    </form>
	    <h3>Lines statistic:</h3>
	         <table class="table table-striped table-hover ">
	        	<thead>
					<tr>
						<th>Line number</th>
						<th>Longest word</th>
						<th>Shortest word</th>
						<th>Average word length</th>
					</tr>
				</thead>
				<c:forEach items="${lines}" var="line">
					<tr>
						<td>${line.id.lineNumberInFile}</td>
						<td>${line.longestWord}</td>
						<td>${line.shortestWord}</td>
						<td><fmt:formatNumber type="number" maxIntegerDigits="3" 
						value="${line.averageWordLenght}"/></td>
					</tr>
				</c:forEach>
			</table>
	    
	</div>
</body>
</html>