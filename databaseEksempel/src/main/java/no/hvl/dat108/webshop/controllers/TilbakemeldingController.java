package no.hvl.dat108.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.services.StemmeService;
import no.hvl.dat108.webshop.util.BrukerUtil;

@Controller
public class TilbakemeldingController {

	@Autowired private StandService standservice;
	
	@Autowired private StemmeService stemmeservice;
	
	@Autowired private BrukerUtil brukerutil;
	
	@GetMapping("/tilbakemelding")
	public String getTilbakemelding(Model model, @RequestParam(required = false) String navn, RedirectAttributes ra) {

		if (navn == null) {
			return "redirect:/home";
		}
		
		if(!standservice.eksistererStand(navn)) {
			return "redirect:/home";
		}
		
		Stand stand = standservice.finnStand(navn);
		model.addAttribute("navn", stand.getNavn());
		
		return "tilbakemelding";
	}
	
	@PostMapping("/tilbakemelding")
    public String postTilbakemelding(
    		Model model, 
    		HttpServletRequest request,
    		@RequestParam(name = "rating", defaultValue = "0") int rating, 
    		@RequestParam(required = false) String navn
    		) {

		if(rating < 0 || rating > 5) {
			return "redirect:/tilbakemelding?navn="+navn;
		}
		
		Stemme stemme = new Stemme(brukerutil.getBrukerId(request), navn, rating);
		stemmeservice.lagreStemme(stemme);
		
		System.out.println(standservice.rangerStander().toString());
		
        model.addAttribute("rating", rating);
        model.addAttribute("navn", navn);

        return "takk";
    }
}
