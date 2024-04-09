<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="no">
<head>
<link rel="stylesheet" href="css/Standard.css">
<title>Standliste</title>
<meta charset="UTF-8">
<style>
/* CSS for table layout */
.container {
	margin: 0 auto; /* Center the container */
	width: 80%; /* Adjust as needed */
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

/* Centering the div */
.center-div {
	margin: 0 auto; /* Center the div horizontally */
	width: 100%; /* Make the div full-width */
	max-width: 600px; /* Adjust as needed */
}

/* Adding background color to th elements */
td {
	background-color: #f2f2f2; /* Light gray background */
}

</style>
</head>
<body>
	<div class="container">
		<h1>Liste av standene på Expoen</h1>
		<p>Her kan du scrolle gjennom alle standene</p>

		<div class="center-div">
			<c:forEach items="${standliste}" var="stand">
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
						<th>QRkode til siden:</th>
						<td><a href="/stand?navn=${stand.navn}"> <img
								src="${stand.qrstand}" alt="QRkode til siden">
						</a></td>
					</tr>
				</table>
				<br>
			</c:forEach>
		</div>
	</div>
</body>
</html>
