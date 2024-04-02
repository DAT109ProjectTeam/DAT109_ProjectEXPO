package no.hvl.dat108.webshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.repos.StandRepo;

@Service
public class StandService {

	@Autowired
	StandRepo standrepo;

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
}