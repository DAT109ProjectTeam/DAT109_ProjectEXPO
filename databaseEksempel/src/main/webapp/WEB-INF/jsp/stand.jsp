<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="no">
<head>
    <title>Registrering av Stand</title>
    <meta charset="UTF-8">
    <script type="text/javascript">
        function convertToEmbeddedLink(normalLink) {
            var videoID;
            var match = normalLink.match(/[?&]v=([a-zA-Z0-9_-]{11})/);
            if (match && match[1]) {
                videoID = match[1];
                return "https://www.youtube.com/embed/" + videoID;
            }
            // If the link doesn't match the expected format, return null or handle it appropriately.
            return null;
        }
    </script>
</head>
<body>
    <h1>Side for stand</h1>
    <p>All info ligger under</p>
    <h2>Navn: ${stand.navn}</h2>
    <p>Beskrivelse: ${stand.beskrivelse}</p>
    <h2>Youtube video</h2>
    <c:if test="${not empty stand.youtubelink}">
        <iframe width="560" height="315" src="<c:out value="${stand.youtubelink}" />" frameborder="0" allowfullscreen></iframe>
    </c:if>
    <form method="GET" action="home">
        <!-- Remove empty fieldset if not needed -->
        <button type="submit">Legg til stand</button>
    </form>
</body>
</html>
