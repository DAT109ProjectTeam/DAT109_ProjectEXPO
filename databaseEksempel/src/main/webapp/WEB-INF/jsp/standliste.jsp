<!DOCTYPE html>
<html lang="no">
    <head>
        <title>Standliste</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Liste av standene på Expoen</h1>
        <p>Her kan du scrolle gjennom alle standene</p>

        <c:forEach items="liste" var="stand">
            <h2>${stand.navn}</h2>
            <p>${stand.beskrivelse}</p>
            <img src=${stand.qrstand} alt="QRkode til siden">
        </c:forEach>
    </body>
</html>