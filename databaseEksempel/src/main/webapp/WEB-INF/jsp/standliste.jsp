<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="no">
<head>
<link rel="stylesheet" href="css/Standard.css">
<title>StandlisteRangert</title>
<meta charset="UTF-8">
<style>
.container {
	margin: 0 auto;
	width: 80%;
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

.center-div {
	margin: 0 auto; /
	width: 100%;
	max-width: 600px;
}

td {
	background-color: #f2f2f2;
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
					<c:if test="${not empty stand.poengsum && stand.poengsum > 0}">
						<th>Poengsum</th>
						<td>${stand.poengsum}</td>
					</c:if>
				</table>
				<br>
			</c:forEach>
		</div>
	</div>
</body>
</html>
