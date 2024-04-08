<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="no">
    <head>
        <title>Standliste</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Liste av standene på Expoen</h1>
        <p>Her kan du scrolle gjennom alle standene</p>

        <c:forEach items="${standliste}" var="stand">
            <h2>${stand.navn}</h2>
            <p>${stand.beskrivelse}</p>
            <p>${stand.qrstand}</p>
            <img src="${stand.qrstand}" alt="QRkode til siden">
        </c:forEach>
    </body>
</html>