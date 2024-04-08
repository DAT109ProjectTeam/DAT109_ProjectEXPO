<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<style>
body {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0;
    background-color: #339c6b;
    font-family: Arial, sans-serif; /* Added font-family for consistency */
}

.container {
    text-align: center;
    margin-top: 50px; /* Adjusted margin for better alignment */
}

.button {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 0.25em;
    cursor: pointer;
    background-color: #008000ad;
    color: white;
    transition: background-color 0.3s;
    margin-top: 15px;
}

.button:hover {
    background-color: #008000;
}

table {
    width: 80%;
    margin: 20px auto; /* Adjusted margins for better alignment */
    border-collapse: collapse;
}

th, td {
    border: 1px solid #b2b2b2;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #008000ad;
    color: white;
}

form {
    margin-top: 20px; /* Adjusted margin for better alignment */
}
</style>
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

		<form action="home" method="GET">
			<button class="button" type="submit" role="button">Tilbake
				til startside</button>
		</form>
	</div>
</body>

</html>