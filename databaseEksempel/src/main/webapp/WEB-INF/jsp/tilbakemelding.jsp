<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
</head>

<body>
    <div>
        <form action="/tilbakemelding" method="POST">
            <p>Please select your rating:</p>
            <input type="hidden" name="navn" value="${navn}">
            <label><input type="radio" name="rating" value="1"> 1</label><br>
            <label><input type="radio" name="rating" value="2"> 2</label><br>
            <label><input type="radio" name="rating" value="3"> 3</label><br>
            <label><input type="radio" name="rating" value="4"> 4</label><br>
            <label><input type="radio" name="rating" value="5"> 5</label><br>
            <button class="button" type="submit" role="button">Submit</button>
        </form>
    </div>
</body>

</html>
