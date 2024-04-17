<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="no">
<head>
<title>Registrering av Stand</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/Standard.css">
<style>
body {
	display: grid;
	justify-content: center;
	align-items: center;
	margin: 0;
	background-color: #339c6b;
}

.container {
	text-align: center;
}

.button {
	padding: 10px 20px;
	font-size: 16px;
}

button {
	padding: 10px 20px;
	border: 1px solid #b2b2b2;
	outline: none;
	border-radius: 0.25em;
	color: white;
	font-size: 15px;
	cursor: pointer;
	margin-top: 15px;
	margin-bottom: 10px;
	background-color: #008000ad; /* Default button color */
	transition: 0.3s background-color;
}

button.red {
	background-color: #ff0000; /* Red button color */
}

button.blue {
	background-color: #0000ff; /* Blue button color */
}
</style>
</head>
<body>
	<div class="container">
		<h1>Admin Panel</h1>
		<p>Her har du tilgang til admin funksjonene under:</p>
		<c:choose>
			<c:when test="${status == false}">
				<form method="GET" action="StartEvent">
					<button type="submit" class="blue">Start Event</button>
				</form>
			</c:when>
			<c:when test="${status == true}">
				<form method="GET" action="StoppEvent">
					<button type="submit" class="red">Stop Event</button>
				</form>
			</c:when>
		</c:choose>
		<form method="POST" action="ChangeCookieDuration">
			<label for=lengde>Varighet på Cookies(timer):</label><br>
			<input id="lengde" name="lengde">
			<button type="submit">Lagre</button>
		</form>

		<form method="GET" action="registrer">
			<button type="submit">Registrer Stand</button>
		</form>

		<form method="GET" action="reset">
			<button type="submit">Reset Database</button>
		</form>
		<form method="GET" action="home">
			<button type="submit">Gå Ut/Tilbake</button>
		</form>
	</div>
</body>
</html>
