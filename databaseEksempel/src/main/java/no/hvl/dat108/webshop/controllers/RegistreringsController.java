package no.hvl.dat108.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import no.hvl.dat108.webshop.objects.Stand;

@Controller
public class RegistreringsController {

	@GetMapping("/registrer")
	public String registreringsskjema(Model model,
			RedirectAttributes ra) {
		
		model.addAttribute("stand", new Stand());
		
		return "registrering";
	}
	
	@PostMapping("/registrer")
	public String registrerSkjema(Model model,
			@Valid @ModelAttribute Stand stand) {
		
		System.out.println(stand.toString());
		
		return "stand";
	}
	
}
