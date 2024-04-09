<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="no">
<head>
<meta charset="UTF-8">
<title>Registrering av Stand</title>
<link rel="stylesheet" href="css/registrer.css">
</head>
<body>
	<div class="container">
		<div class="logo-container">
			<img
				src="https://t4.ftcdn.net/jpg/05/24/88/53/360_F_524885373_R8zFwFRm7phP46V79ZixQtp0nb056w7o.jpg"
				alt="HVL Logo" class="hvl-logo">
		</div>
		<div class="top-right-logo">
                    <img src="https://helseforsking.no/wp-content/uploads/2017/01/logo-hvl-1030x454.jpg" alt="Høyre Logo" class="høyre-logo">
                </div>
		<h1>Registrer en stand</h1>
		<p>Her kan du registrere en stand du vil legge til på expo</p>
		<form:form method="POST" action="registrer" modelAttribute="stand">
			<fieldset>
				<div>
					<p style="color: red">${feilmelding}</p>
					<label for="navn">Navn:</label>
					<form:input type="text" id="navn" path="navn" />
				</div>
				<div>
					<label for="beskrivelse">Beskrivelse:</label>
					<form:input type="text" id="beskrivelse" path="beskrivelse" />
				</div>
				<div>
					<label for="youtubelink">YouTube Embed Url:</label>
					<form:input type="text" id="youtubelink" path="youtubelink" />
				</div>
				<button type="submit">Registrer</button>
			</fieldset>

		</form:form>
		<form method="GET" action="home">
			<button type="submit">Tilbake til startsiden</button>
		</form>
	</div>
</body>
</html>
