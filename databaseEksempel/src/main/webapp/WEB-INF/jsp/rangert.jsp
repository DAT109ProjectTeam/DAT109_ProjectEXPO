<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/Standard.css">
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
	display: grid;
}

td {
	background-color: #f2f2f2;
}
</style>
</head>

<body>
	<div class="container">
	<div class="center-div">
		<h1>List of Stands</h1>
		<table border="1">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<!-- <th>YouTube Link</th> -->
					<!-- <th>QR Kode Stand</th> -->
					<!-- <th>QR Kode Tilbakemelding</th> -->
					<th>Poengsum</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${standliste}" var="stand">
					<tr>
						<td>${stand.navn}</td>
						<td>${stand.beskrivelse}</td>
						<%-- <td>${stand.youtubelink}</td> --%>
						<%-- <td>${stand.qrstand}</td> --%>
						<%-- <td>${stand.qrtilbakemelding}</td> --%>
						<td>${stand.poengsum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="home" method="GET">
			<button class="button" type="submit" role="button">Tilbake
				til startside</button>
		</form>
		</div>
	</div>
</body>

</html>