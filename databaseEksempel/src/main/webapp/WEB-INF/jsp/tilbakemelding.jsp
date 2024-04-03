<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<style>
body {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0;
    background-color: #339c6b;
    font-family: Arial, sans-serif; /* Added font-family for consistency */
}

.container {
    text-align: center;
    margin-top: 50px; /* Adjusted margin for better alignment */
}

.button {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 0.25em;
    cursor: pointer;
    background-color: #008000ad;
    color: white;
    transition: background-color 0.3s;
    margin-top: 15px;
}

.button:hover {
    background-color: #008000;
}

table {
    width: 80%;
    margin: 20px auto; /* Adjusted margins for better alignment */
    border-collapse: collapse;
}

th, td {
    border: 1px solid #b2b2b2;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #008000ad;
    color: white;
}

form {
    margin-top: 20px; /* Adjusted margin for better alignment */
}
</style>
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
