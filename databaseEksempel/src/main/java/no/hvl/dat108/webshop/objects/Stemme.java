package no.hvl.dat108.webshop.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema="dat109")
public class Stemme {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	
	public Stemme(Integer id, Integer brukerid, String navn, int verdi) {
		this.id = id;
		this.brukerid = brukerid;
		this.navn = navn;
		this.verdi = verdi;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Stemme [id=" + id + ", brukerid=" + brukerid + ", navn=" + navn + ", verdi=" + verdi + "]\n";
	}

	
}
