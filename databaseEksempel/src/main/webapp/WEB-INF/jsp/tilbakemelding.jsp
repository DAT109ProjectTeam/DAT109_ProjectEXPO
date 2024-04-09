<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link rel="stylesheet" href="css/Standard.css">
<style>
body {
	margin: 0;
	display: grid;
	height: 100vh;
	/* Make the grid container cover the full height of the viewport */
	place-items: center;
	/* Align content both horizontally and vertically */
}

.center-div {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.bakgrunn {
    background-color: #ccc;
    border-radius: 10px;
    padding: 10px;
}

table {
	width: 80%;
	margin: 20px auto;
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

/* Style for radio buttons */
.rating-label {
	display: inline-block;
	margin-right: 10px;
	position: relative;
	padding-left: 30px;
	cursor: pointer;
	font-size: 18px;
}

.rating-label input {
	position: absolute;
	opacity: 0;
	cursor: pointer;
}

/* Custom radio button design */
.checkmark {
	position: absolute;
	top: 0;
	left: 0;
	height: 25px;
	width: 25px;
	background-color: #eee;
	border-radius: 50%;
}

/* When radio button checked, change background color */
.rating-label input:checked ~ .checkmark {
	background-color: #2196F3;
}

/* Create the checkmark/indicator */
.checkmark:after {
	content: "";
	position: absolute;
	display: none;
}

/* Show the checkmark when radio button checked */
.rating-label input:checked ~ .checkmark:after {
	display: block;
}

/* Style the checkmark/indicator */
.rating-label .checkmark:after {
	top: 9px;
	left: 9px;
	width: 8px;
	height: 8px;
	border-radius: 50%;
	background: white;
}
</style>
</head>

<body>
	<div class="center-div">
		<form id="ratingForm" action="tilbakemelding" method="POST">
			<p>Please select your rating:</p>
			<p style="color: red">${feilmelding}</p>
			<div class="bakgrunn">
				<input type="hidden" name="navn" value="${navn}"> <label
					class="rating-label"><input type="radio" name="rating"
					value="1"> <span class="checkmark"></span>1</label> <label
					class="rating-label"><input type="radio" name="rating"
					value="2"> <span class="checkmark"></span>2</label> <label
					class="rating-label"><input type="radio" name="rating"
					value="3"> <span class="checkmark"></span>3</label> <label
					class="rating-label"><input type="radio" name="rating"
					value="4"> <span class="checkmark"></span>4</label> <label
					class="rating-label"><input type="radio" name="rating"
					value="5"> <span class="checkmark"></span>5</label>
			</div>
			<button class="button" type="submit" role="button">Submit</button>
		</form>
	</div>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var form = document.getElementById('ratingForm');
			form.addEventListener("submit", function(event) {
				var ratingButtons = document
						.querySelectorAll('input[name="rating"]');
				var isChecked = false;
				for (var i = 0; i < ratingButtons.length; i++) {
					if (ratingButtons[i].checked) {
						isChecked = true;
						break;
					}
				}
				if (!isChecked) {
					alert("Please select a rating before submitting.");
					event.preventDefault();
				}
			});
		});
	</script>
</body>

</html>
