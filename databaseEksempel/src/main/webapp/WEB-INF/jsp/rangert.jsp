<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
</head>

<body>
	<div>
		<h1>List of Stands</h1>
		<table border="1">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>YouTube Link</th>
					<th>QR Kode Stand</th>
					<th>QR Kode Tilbakemelding</th>
					<th>Poengsum</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${standliste}" var="stand">
					<tr>
						<td>${stand.navn}</td>
						<td>${stand.beskrivelse}</td>
						<td>${stand.youtubelink}</td>
						<td>${stand.qrstand}</td>
						<td>${stand.qrtilbakemelding}</td>
						<td>${stand.poengsum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="/home" method="GET">
			<button class="button" type="submit" role="button">Tilbake
				til startside</button>
		</form>
	</div>
</body>

</html>