<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="css/Standard.css">
<style>
.center-div {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>

<body>
	<div class="center-div">
        <p>Takk for tilbakemelding</p>
        <p>Du gav ${navn}</p>
        <p>${rating}</p>
        <p>poeng</p>
        <form action="home" method="GET">
            <button class="button" type="submit" role="button">Tilbake til startside</button>
        </form>
    </div>
</body>

</html>