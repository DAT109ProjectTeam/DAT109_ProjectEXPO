package no.hvl.dat108.webshop.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import no.hvl.dat108.webshop.objects.Stand;

public interface StandRepo extends JpaRepository<Stand, String> {

	Stand findByNavn(String navn);

	List<Stand> findAll();
}
