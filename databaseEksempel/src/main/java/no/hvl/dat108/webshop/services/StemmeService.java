package no.hvl.dat108.webshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.repos.StemmeRepo;

@Service
public class StemmeService {

	@Autowired
	StemmeRepo stemmerepo;

	public List<Stemme> finnAlleStemmer() {
		return stemmerepo.findAll();
	}

	public Stemme finnStemme(Integer id) {
		return stemmerepo.findByBrukerid(id);
	}

	public boolean eksistererStemme(Integer id, String navn) {

		Stemme stemme = stemmerepo.findByBrukeridAndNavn(id, navn);

		if (stemme == null) {
			return false;
		}

		return true;
	}

	public void lagreStemme(Stemme stemme) {

		if(stemme.getBrukerid() == -1) {
			return;
		}
		
		if (!eksistererStemme(stemme.getBrukerid(), stemme.getNavn())) {
			stemmerepo.save(stemme);
		} else {
			Stemme stemmen = stemmerepo.findByBrukeridAndNavn(stemme.getBrukerid(), stemme.getNavn());
			stemmerepo.deleteById(stemmen.getId());
			stemmerepo.save(stemme);
		}
	}
}