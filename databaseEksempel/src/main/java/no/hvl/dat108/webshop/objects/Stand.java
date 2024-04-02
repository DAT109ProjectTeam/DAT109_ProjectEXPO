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
		this.qrstand = "";
		this.qrtilbakemelding = "";
		poengsum = 0;
	}

	public Stand(String navn, String beskrivelse, String youtubelink, String qrstand, String qrtilbakemelding,
			Integer poengsum) {
		super();
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.youtubelink = youtubelink;
		this.qrstand = qrstand;	
		this.qrtilbakemelding = qrtilbakemelding;
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

	public String getYoutubelink() {
		return youtubelink;
	}

	public void setYoutubelink(String youtubelink) {
		this.youtubelink = youtubelink;
	}

	public String getQrstand() {
		return qrstand;
	}

	public void setQrstand(String qrstand) {
		this.qrstand = qrstand;
	}

	public String getQrtilbakemelding() {
		return qrtilbakemelding;
	}

	public void setQrtilbakemelding(String qrtilbakemelding) {
		this.qrtilbakemelding = qrtilbakemelding;
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
