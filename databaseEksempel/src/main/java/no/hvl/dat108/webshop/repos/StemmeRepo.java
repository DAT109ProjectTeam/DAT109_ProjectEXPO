package no.hvl.dat108.webshop.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.objects.Stemme;

public interface StemmeRepo extends JpaRepository<Stemme, Integer>{
	Stemme findByBrukerid(Integer id);
	
	Stemme findByBrukeridAndNavn(Integer id, String navn);
	
	List<Stemme> findAll();
}
