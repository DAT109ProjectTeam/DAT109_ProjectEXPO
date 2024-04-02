package no.hvl.dat108.webshop.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema="dat109")
public class Stemme {

	@Id
	private Integer brukerid;
	private String navn;
	private int verdi;
	
	public Stemme() {
		
	}
	
	public Stemme(Integer brukerid, String navn, int verdi) {
		this.brukerid = brukerid;
		this.navn = navn;
		this.verdi = verdi;
	}

	public int getBrukerid() {
		return brukerid;
	}

	public void setBrukerid(Integer brukerid) {
		this.brukerid = brukerid;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getVerdi() {
		return verdi;
	}

	public void setVerdi(int verdi) {
		this.verdi = verdi;
	}

	
}
