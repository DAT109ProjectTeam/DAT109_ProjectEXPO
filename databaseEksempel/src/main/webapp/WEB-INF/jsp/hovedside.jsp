<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<head>
<link rel="stylesheet" href="css/hovedside.css">
<link rel="stylesheet" href="css/Standard.css">
</head>
</head>

<body>
	<div class="logo-container">
		<img
			src="https://t4.ftcdn.net/jpg/05/24/88/53/360_F_524885373_R8zFwFRm7phP46V79ZixQtp0nb056w7o.jpg"
			alt="HVL Logo" class="hvl-logo">
	</div>
	<div class="container">

		<h1>EXPO_2024</h1>
		<p>Velkommen til HVL Expo2024</p>
		<p style="color: red">${feilmelding}</p>
		<c:if test="${Rolle != 'Admin' && Rolle != 'Jury'}">
			<div class="qrscanner">
				<h3>SKANN QR-KODE</h3>
				<div id="my-qr-reader"></div>
				<button id="start-scanner">Start Skanner</button>
			</div>
			<script src="https://unpkg.com/html5-qrcode"></script>
			<script>
            const qrCodeReader = new Html5Qrcode('my-qr-reader');

            function onScanSuccess(qrCodeMessage) {
                // Handle successful scan
                //alert(`QR Code detected: ${qrCodeMessage}`);

                window.location.href = qrCodeMessage;

                qrCodeReader.stop().then(ignore => {
                    // QR Code reader stopped successfully.
                }).catch(err => {
                    // Failed to stop QR Code reader.
                    console.error(err);
                });
            }

            function onScanError(error) {

            }

            document.getElementById('start-scanner').addEventListener('click', function() {
                qrCodeReader.start({
                        facingMode: "environment"
                    }, // use the rear camera if available
                    {
                        fps: 10
                    }, // Scan FPS rate
                    onScanSuccess,
                    onScanError
                );
            });
        </script>
			<form action="standlist	e" method="GET">
				<button class="button" role="button" type="submit">Liste
					over Stands</button>
			</form>
		</c:if>

		<c:if test="${Rolle == 'Admin'}">
			<div>
				<p style="color: green">Du er en administrator</p>
				<form action="RangertVisning" method="GET">
					<button class="button" role="button" type="submit">RangertVisning</button>
				</form>
				<form action="AdminSide" method="GET">
					<button class="button" role="button" type="submit">Admin
						Side</button>
				</form>
			</div>
		</c:if>

		<br>

		<c:if test="${Rolle == 'Jury'}">
			<p style="color: green">Du er med i Juryen</p>
			<form action="RangertVisning" method="GET">
				<button class="button" role="button" type="submit">RangertVisning</button>
			</form>
		</c:if>
		<%-- <p>Value of 'Rolle' cookie: ${Rolle}</p>
		<p>Value of 'ID' cookie: ${ID}</p>

		<p>
			For øyeblikket er Admin/Jury controllerene satt til: <br>
			localhost:8080/Admin <br> og <br> localhost:8080/Jury <br>
			som vil forandre rollen en bruken har
		</p> --%>
	</div>
</body>

</html>