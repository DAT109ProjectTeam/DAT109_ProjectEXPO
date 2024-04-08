<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="no">
<head>
<title>Registrering av Stand</title>
<meta charset="UTF-8">
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
	background-color: #008000ad;
	transition: 0.3s background-color;
}

button:hover {
	background-color: #008000;
}
</style>
</head>
<body>
	<h1>Admin Panel</h1>
	<p>Her har du tilgang til admin funksjonene under:</p>

	<form method="POST" action="startevent">
		<button type="submit">Start Event</button>
	</form>

	<form method="POST" action="registrer">
		<button type="submit">Registrer Stand</button>
	</form>

	<form method="POST" action="reset">
		<button type="submit">Reset Database</button>
	</form>

	<form method="POST" action="stoppevent">
		<button type="submit">Stop Event</button>
	</form>
	<br>
	<br>
	<br>
	<form method="GET" action="home">
		<button type="submit">G� Ut/Tilbake</button>
	</form>
</body>
</html>