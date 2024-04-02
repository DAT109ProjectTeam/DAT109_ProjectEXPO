package no.hvl.dat108.webshop.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import no.hvl.dat108.webshop.util.QRGenerator;

@Entity
@Table(schema="dat109")
public class Stand {

	@Id
	private String navn;
	
	private String beskrivelse;
	
	private String youtubelink;
	
	private String qrstand;
	
	private String qrtilbakemelding;
	
	private Integer poengsum;

	public Stand() {
		
	}
	
	public Stand(String navn, String beskrivelse, String youtubeLink) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.youtubelink = youtubeLink;
		setQrStand(navn);
		setQrTilbakemelding(navn);
		poengsum = 0;
	}

	public Stand(String navn, String beskrivelse, String youtubelink,
			Integer poengsum) {
		super();
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.youtubelink = youtubelink;
		setQrStand(navn);	
		setQrTilbakemelding(navn);
		this.poengsum = poengsum;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getYoutubeLink() {
		return youtubelink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubelink = youtubeLink;
	}

	public String getQrStand() {
		return qrstand;
	}

	public void setQrStand(String navn) {
		this.qrstand = "src/main/webapp/WEB-INF/QRKoder/" + navn + "StandQRKode.jpg";
		String QR_Path = this.qrstand;
		QRGenerator.generateQR("http://localhost:8080/stand?navn=" + navn, 350, 350, QR_Path);
	}

	public String getQrTilbakemelding() {
		return qrtilbakemelding;
	}

	public void setQrTilbakemelding(String navn) {
		this.qrtilbakemelding = "src/main/webapp/WEB-INF/QRKoder/" + navn + "TilbakemeldingQRKode.jpg";
		String QR_Path = this.qrtilbakemelding;
		QRGenerator.generateQR("http://localhost:8080/tilbakemelding?navn=" + navn, 350, 350, QR_Path);
	}

	public Integer getPoengsum() {
		return poengsum;
	}

	public void setPoengsum(Integer poengsum) {
		this.poengsum = poengsum;
	}

	@Override
	public String toString() {
		return navn + " score: " + poengsum + "\n";
	}
	
	
	
}
