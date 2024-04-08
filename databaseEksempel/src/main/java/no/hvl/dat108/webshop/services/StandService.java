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
import no.hvl.dat108.webshop.util.QRGenerator;

@Service
public class StandService {

	@Autowired StandRepo standrepo;
	
	@Autowired StemmeRepo stemmerepo;
	
	@Autowired QRGenerator qrgenerator;

	public List<Stand> finnAlleStands() {
		return standrepo.findAll();
	}
	
	public void lagreStand(Stand stand) {
		
		if(!eksistererStand(stand.getNavn())) {
			qrgenerator.genererQrStand(stand);
			qrgenerator.genererQrTilbakemelding(stand);
			standrepo.save(stand);
		} else {
			standrepo.deleteById(stand.getNavn());
			qrgenerator.genererQrStand(stand);
			qrgenerator.genererQrTilbakemelding(stand);
			standrepo.save(stand);
		}
		
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
	
	public void resetDatabase() {
		
		List<Stand> liste = standrepo.findAll();
		for(Stand stand : liste) {
			standrepo.deleteById(stand.getNavn());
		}

		//TA VEKK SEINARE
		Stand stand1 = new Stand("Stand1", "tester", "cat");
		Stand stand2 = new Stand("Stand2", "tester", "cat");
		Stand stand3 = new Stand("Stand3", "tester", "cat");
		qrgenerator.genererQrStand(stand1);
		qrgenerator.genererQrTilbakemelding(stand1);
		qrgenerator.genererQrStand(stand2);
		qrgenerator.genererQrTilbakemelding(stand2);
		qrgenerator.genererQrStand(stand3);
		qrgenerator.genererQrTilbakemelding(stand3);
		standrepo.save(stand1);
		standrepo.save(stand2);
		standrepo.save(stand3);
		
	}
}