package no.hvl.dat108.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
			@Valid @ModelAttribute Stand stand,
			RedirectAttributes ra) {
		
		if(stand.getNavn() == null || stand.getNavn().equals("")) {
			ra.addFlashAttribute("feilmelding", "Må ha et navn på stand");
			return "redirect:/registrer";
		}
		
		standservice.lagreStand(stand);
		
		return "stand";
	}
	
}
