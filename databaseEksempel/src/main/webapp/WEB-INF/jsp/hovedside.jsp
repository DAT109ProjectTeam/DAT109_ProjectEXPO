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
}

.container {
	text-align: center;
}

.button {
	padding: 10px 20px;
	font-size: 16px;
}

.qrscanner {
	width: 100%;
	max-width: 500px;
	margin: 5px;
}

.qrscanner h1 {
	color: #ffffff;
}

.qrsections {
	background-color: #ffffff;
	padding: 50px 30px;
	border: 1.5px solid #b2b2b2;
	width: 500px;
	height: 400px;
	border-radius: 0.25em;
	box-shadow: 0 20px 25px rgba(0, 0, 0, 0.25);
}

#my-qr-reader {
	padding: 20px !important;
	border: 1.5px solid #b2b2b2 !important;
	border-radius: 8px;
	width: 440px;
	height: 340px;
}

#my-qr-reader img[alt="Info icon"] {
	display: none;
}

#my-qr-reader img[alt="Camera based scan"] {
	width: 200px !important;
	height: 200px !important;
}

button {
	padding: 10px 20px;
	border: 1px solid #b2b2b2;
	outline: none;
	border-radius: 0.25em;
	color: white;
	font-size: 15px;
	cursor: pointer;
	margin-top: 15px;
	margin-bottom: 10px;
	background-color: #008000ad;
	transition: 0.3s background-color;
}

button:hover {
	background-color: #008000;
}

#html5-qrcode-anchor-scan-type-change {
	text-decoration: none !important;
	color: #1d9bf0;
}

video {
	width: 100% !important;
	border: 1px solid #b2b2b2 !important;
	border-radius: 0.25em;
}
</style>
</head>

<body>
	<div class="container">

		<h1>EXPO_2024</h1>
		<p>Velkommen til HVL Expo2024</p>

		<c:if test="${Rolle != 'Admin' && Rolle != 'Jury'}">
			<div class="qrscanner">
				<h1>Skann QR-Kode</h1>
				<div class="qrsections">
					<div id="my-qr-reader"></div>
					<button id="start-scanner">Start Skanner</button>
				</div>
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
		</c:if>

		<c:if test="${Rolle == 'Admin'}">
			<div class="spectator-button">
				<p>Du er med i juryen</p>
				<a href="spectatorlogin">
					<button class="button" type="submit" role="button">Tilskuer(Spectator)</button>
				</a>
			</div>
		</c:if>

		<br>

		<!-- <div class="exhibitor-button">
			<a href="exhibitorlogin">
				<button class="button" role="button">Utstiller (Exhibitor)</button>
			</a>
		</div>
		<br> -->

		<c:if test="${Rolle == 'Jury'}">
			<form class="jury-button" action="RangertVisning" method="GET">
				<p>Test</p>
				<button class="button" role="button" type="submit" >RangertVisning</button>
			</form>
		</c:if>

		<p>Value of 'Rolle' cookie: ${Rolle}</p>
		<p>Value of 'ID' cookie: ${ID}</p>

		<p>
			For øyeblikket er Admin/Jury controllerene satt til: <br>
			localhost:8080/Admin <br> og <br> localhost:8080/Jury <br>
			som vil forandre rollen en bruken har
		</p>

	</div>
</body>

</html>