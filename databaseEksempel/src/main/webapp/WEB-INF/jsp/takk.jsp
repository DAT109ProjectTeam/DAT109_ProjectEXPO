<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
</head>

<body>
	<div>
        <p>Takk for tilbakemelding</p>
        <p>Du gav ${navn}</p>
        <p>${rating}</p>
        <p>poeng</p>
        <form action="/home" method="GET">
            <button class="button" type="submit" role="button">Tilbake til startside</button>
        </form>
    </div>
</body>

</html>