<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="css/Standard.css">
</head>

<body>
	<div class="adminpassord">
		<form action="Admin" method="POST">
			<p>Passord:</p>
			<input type="password" name="passord" placeholder="Skriv inn passord"></input>
			<br>
			<button class="button" type="submit" role="button">Send</button>
		</form>
	</div>
</body>

</html>