package no.hvl.dat108.webshop.controllers;

import no.hvl.dat108.webshop.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.services.StemmeService;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class TilbakemeldingController implements ErrorController {

	@Autowired private StandService standservice;
	
	@Autowired private StemmeService stemmeservice;
	
	@Autowired private BrukerUtil brukerutil;
	
	@Autowired private RolleUtil rolleutil;
	
	@GetMapping("/tilbakemelding")
	public String getTilbakemelding(Model model, 
			@RequestParam(required = false) String navn, 
			RedirectAttributes ra,
			HttpServletRequest request,
			HttpServletResponse response
			) {

		if (navn == null) {
			ra.addFlashAttribute("feilmelding", "Ugyldig url/navn på stand");
			return "redirect:/home";
		}
		
		if(!standservice.eksistererStand(navn)) {
			ra.addFlashAttribute("feilmelding", "Ugyldig navn på stand");
			return "redirect:/home";
		}
		
		if(!AdminRolleController.ErEventetAktivt) {
			ra.addFlashAttribute("feilmelding", "Eventet er ikke startet");
			return "redirect:/home";
		}
		
		brukerutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(rolle.equals("Admin") || rolle.equals("Jury")) {
			ra.addFlashAttribute("feilmelding", "Du har ikke tilgang til stemming");
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
			HttpServletResponse response,
			RedirectAttributes ra,
    		@RequestParam(name = "rating", defaultValue = "0") int rating, 
    		@RequestParam(required = false) String navn
    		) {

		if(rating <= 0 || rating > 5) {
			ra.addFlashAttribute("feilmelding", "Venligst velg en verdi 1-5");
			return "redirect:/tilbakemelding?navn="+navn;
		}
		
		brukerutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!AdminRolleController.ErEventetAktivt) {
			ra.addFlashAttribute("feilmelding", "Eventet er ikke startet");
			return "redirect:/home";
		}
		
		if(rolle.equals("Admin") || rolle.equals("Jury")) {
			ra.addFlashAttribute("feilmelding", "Du har ikke tilgang til stemming");
			return "redirect:/home";
		}
		
		Stemme stemme = new Stemme(brukerutil.getBrukerId(request), navn, rating);
		stemmeservice.lagreStemme(stemme);
		
        model.addAttribute("rating", rating);
        model.addAttribute("navn", navn);

        return "takk";
    }
	
	@RequestMapping("/error")
    public String handleError() {
        // You can direct to a specific controller or return a specific view here
        // For example:
        // return "errorPage"; // Assuming you have a view named errorPage
        return "redirect:home"; // Redirect to a specific controller
    }

    public String getErrorPath() {
    	return "/error";
    }
}
