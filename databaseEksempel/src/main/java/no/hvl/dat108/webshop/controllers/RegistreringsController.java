package no.hvl.dat108.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.util.QRGenerator;

@Controller
public class RegistreringsController {

	@Autowired StandService standservice;
	
	@Autowired QRGenerator qrgenerator;
	
	@GetMapping("/registrer")
	public String registreringsskjema(Model model,
			RedirectAttributes ra) {
		
		model.addAttribute("stand", new Stand());
		
		return "registrer";
	}
	
	@PostMapping("/registrer")
	public String registrerSkjema(Model model,
			@Valid @ModelAttribute Stand stand) {
		
		if(stand.getNavn() == null) {
			return "redirect:/registrer";
		}
		
		standservice.lagreStand(stand);
		
		return "stand";
	}
	
}
