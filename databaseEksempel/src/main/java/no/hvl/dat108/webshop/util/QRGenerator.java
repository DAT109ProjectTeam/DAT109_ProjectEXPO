package no.hvl.dat108.webshop.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import no.hvl.dat108.webshop.objects.Stand;

@Component
public class QRGenerator {

    @Value("${qr.codes.directory}")
    private String qrCodesDirectory;

    public static void generateQR(String text, int width, int height, Path qrPath) {
        QRCodeWriter qrWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            BitMatrix bitMatrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrPath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public void genererQrStand(Stand stand) {
        String qrFileName = stand.getNavn() + "StandQRKode.png";
        Path qrPath = Paths.get(qrCodesDirectory, qrFileName);
        String qrUrl = "http://localhost:8080/stand?navn=" + stand.getNavn();
        generateQR(qrUrl, 350, 350, qrPath);
        stand.setQrstand(qrFileName);
    }

    public void genererQrTilbakemelding(Stand stand) {
        String qrFileName = stand.getNavn() + "TilbakemeldingQRKode.png";
        Path qrPath = Paths.get(qrCodesDirectory, qrFileName);
        String qrUrl = "http://localhost:8080/tilbakemelding?navn=" + stand.getNavn();
        generateQR(qrUrl, 350, 350, qrPath);
        stand.setQrtilbakemelding(qrFileName);
    }
    
    public void reset() {
        try {
            // Define the directory path
            Path directoryPath = Paths.get(qrCodesDirectory);

            // Iterate through the directory and its subdirectories
            Files.walk(directoryPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".png"))
                    .forEach(this::deleteFile);
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    private void deleteFile(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Handle IOException for individual file deletion
            e.printStackTrace();
        }
    }
}
