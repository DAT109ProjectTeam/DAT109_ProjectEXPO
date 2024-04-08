package no.hvl.dat108.webshop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class StandController {

	@Autowired private StandService ss;
	
	@Autowired private StandService standservice;
	
	@Autowired private BrukerUtil brukeridutil;
	
	@Autowired private RolleUtil rolleutil;

	@GetMapping("/stand")
	public String getStand(Model model) {

		Stand stand = ss.finnStand("Stand1");

		model.addAttribute("stand", stand);

		return "stand";
	}
	
	@GetMapping("/standliste")
	public String getStandListe(Model model) {

		List<Stand> standliste = ss.finnAlleStands();

		model.addAttribute("standliste", standliste);

		return "standliste";
	}

	@GetMapping("/RangertVisning")
	public String visRangerteStands(Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes ra) {

		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);

		if (!rolle.equals("Jury") && !rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding", "Du har ikke tilgang"); 
			return "redirect:";
		}
			

		List<Stand> rangerteStander = standservice.rangerStander();

		model.addAttribute("standliste", rangerteStander);

		return "rangert";
	}
}
