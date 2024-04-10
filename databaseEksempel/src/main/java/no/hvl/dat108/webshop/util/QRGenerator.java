package no.hvl.dat108.webshop.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ServletContext servletContext;

    public void generateAndSaveQR(String text, int width, int height, String fileName) {
        try {
            // Get the real path of the directory where the QR codes will be saved
            String directoryPath = servletContext.getRealPath(qrCodesDirectory);

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Construct the full path of the QR code file
            String filePath = directoryPath + File.separator + fileName;

            // Create a QR code writer and generate the QR code
            QRCodeWriter qrWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            // Write the QR code to the file
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Paths.get(filePath));

        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    public void genererQrStand(Stand stand) {
    	String contextPath = request.getContextPath();
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), contextPath);
        String qrUrl = baseUrl + "/stand?navn=" + stand.getNavn();
        String qrFileName = stand.getNavn() + "StandQRKode.png";
        generateAndSaveQR(qrUrl, 350, 350, qrFileName);
        stand.setQrstand(qrFileName);
    }

    public void genererQrTilbakemelding(Stand stand) {
    	String contextPath = request.getContextPath();
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), contextPath);
        String qrUrl = baseUrl + "/stand?navn=" + stand.getNavn();
        String qrFileName = stand.getNavn() + "TilbakemeldingQRKode.png";
        generateAndSaveQR(qrUrl, 350, 350, qrFileName);
        stand.setQrtilbakemelding(qrFileName);
    }
    
    public void reset() {
        try {
            // Get the real path of the static folder within the WAR file
            String realPath = servletContext.getRealPath("/WEB-INF/classes/static");

            // Check if realPath is null (e.g., if the WAR is not expanded)
            if (realPath != null) {
                Path directoryPath = Paths.get(realPath);

                Files.walk(directoryPath)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().toLowerCase().endsWith(".png"))
                        .forEach(this::deleteFile);
            } else {
                // Handle case where realPath is null
                // For example, log an error or throw an exception
            }
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
