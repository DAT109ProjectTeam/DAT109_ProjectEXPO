package no.hvl.dat108.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.services.StandService;

@Controller
public class StandController {

	@Autowired StandService ss;
	
	@GetMapping("/stand")
	public String getStand(Model model) {
		
		Stand stand = ss.finnStand("Stand1");
		
		model.addAttribute("stand", stand);
		
		return "stand";
	}
}
