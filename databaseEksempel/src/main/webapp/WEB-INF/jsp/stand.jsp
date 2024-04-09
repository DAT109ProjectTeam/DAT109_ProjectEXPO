<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="no">
<head>
<link rel="stylesheet" href="css/Standard.css">
<title>Registrering av Stand</title>
<meta charset="UTF-8">
<style>
th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<div class="container">
		<table>
			<tr>
				<th>Navn:</th>
				<td>${stand.navn}</td>
			</tr>
			<tr>
				<th>Beskrivelse:</th>
				<td>${stand.beskrivelse}</td>
			</tr>
			<tr>
				<th>Youtube video:</th>
				<td><c:if test="${not empty stand.youtubelink}">
                    ${stand.youtubelink}
                </c:if></td>
			</tr>
			<tr>
				<th>QRkode til siden:</th>
				<td><a href="/tilbakemelding?navn=${stand.navn}"> <img	
						src="${stand.qrtilbakemelding}" alt="QRkode til siden">
				</a></td>
			</tr>
		</table>
		<form method="GET" action="home">
			<button type="submit">Tilbake til startsiden</button>
		</form>
	</div>
</body>
</html>
