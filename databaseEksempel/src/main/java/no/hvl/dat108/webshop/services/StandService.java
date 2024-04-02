package no.hvl.dat108.webshop.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.repos.StandRepo;
import no.hvl.dat108.webshop.repos.StemmeRepo;

@Service
public class StandService {

	@Autowired StandRepo standrepo;
	
	@Autowired StemmeRepo stemmerepo;

	public List<Stand> finnAlleStands() {
		return standrepo.findAll();
	}

	public Stand finnStand(String navn) {
		return standrepo.findByNavn(navn);
	}

	public boolean eksistererStand(String navn) {

		Stand stand = standrepo.findByNavn(navn);

		if (stand == null) {
			return false;
		}

		return true;
	}
	
	public List<Stand> rangerStander(){
		
		List<Stand> standliste = standrepo.findAll();
		List<Stemme> stemmeliste = stemmerepo.findAll();
		
		if(standliste == null || stemmeliste == null) {
			return null;
		}
		
		for(Stand stand : standliste) {
			int poengsum = 0;
			String standnavn = stand.getNavn();
			for(Stemme stemme : stemmeliste) {
				
				if(stemme.getNavn().equals(standnavn)) {
					poengsum += stemme.getVerdi();
				}
				
			}
			stand.setPoengsum(poengsum);
			standrepo.save(stand);
		}
		
		standliste.sort(Comparator.comparingInt(Stand::getPoengsum).reversed());
		
		return standliste;
	}
}