package no.hvl.dat108.webshop.util;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


@Component
public class QRGenerator {
	
	public static void generateQR(String text, int width, int heigth,String qr_path) {
		QRCodeWriter qrwriter = new QRCodeWriter();
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.MARGIN, 1);
		
		
		try {
			BitMatrix bitMatrix = qrwriter.encode(text, BarcodeFormat.QR_CODE, width, heigth,hints);
			
			File qrCodeFile = new File(qr_path);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodeFile.toPath());
		} catch (WriterException | IOException e){
			e.printStackTrace();
		}
		
	}
	public String genererQrStand(String navn) {
		String qrkode = "src/main/webapp/WEB-INF/QRKoder/" + navn + "StandQRKode.jpg";
		String QR_Path = qrkode;
		QRGenerator.generateQR("http://localhost:8080/stand?navn=" + navn, 350, 350, QR_Path);
		return qrkode;
	}

	public String genererQrTilbakemelding(String navn) {
		String qrkode = "src/main/webapp/WEB-INF/QRKoder/" + navn + "TilbakemeldingQRKode.jpg";
		String QR_Path = qrkode;
		QRGenerator.generateQR("http://localhost:8080/tilbakemelding?navn=" + navn, 350, 350, QR_Path);
		return qrkode;
	}
}
