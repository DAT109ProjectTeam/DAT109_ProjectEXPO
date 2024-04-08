<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="no">
<head>
    <title>Registrering av Stand</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Registrer en stand</h1>
    <p>Her kan du registrere en stand du vil legge til p� expo</p>
    <form:form method="POST" action="registrer" modelAttribute="stand">
        <fieldset>
            <div>
            	<p style="color:red">${feilmelding}</p>
                <label for="navn">Navn:</label>
                <form:input type="text" id="navn" path="navn" />
            </div>
            <div>
                <label for="beskrivelse">Beskrivelse:</label>
                <form:input type="text" id="beskrivelse" path="beskrivelse" />
            </div>
            <div>
                <label for="youtubelink">YouTube Link:</label>
                <form:input type="text" id="youtubelink" path="youtubelink" />
            </div>
            <button type="submit">Registrer</button>	
        </fieldset>
        
    </form:form>
    <form method="GET" action="home">
    	<button type="submit">Tilbake til startsiden</button>
    </form>
</body>
</html>
