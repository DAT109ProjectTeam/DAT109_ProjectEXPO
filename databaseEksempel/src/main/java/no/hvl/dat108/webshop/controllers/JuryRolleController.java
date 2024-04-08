package no.hvl.dat108.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class JuryRolleController {

	@Autowired private StandService standservice;
	
	@Autowired private BrukerUtil brukeridutil;
	
	@Autowired private RolleUtil rolleutil;
	
	@GetMapping("/Jury")
	public String getJuryRolleController(
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response, 
			RedirectAttributes ra) {

		brukeridutil.sjekkBruker(request, response, model);
		rolleutil.sjekkRolle(request, response, model);
		
		Cookie[] cookies = request.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Rolle")) {
					cookieValue = cookie.getValue();
					break;
				}
			}
			
			cookieValue = "Jury";
			Cookie cookie = new Cookie("Rolle", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(20);
			response.addCookie(cookie);
			
		}

		return "redirect:/home";
	}
	
	@GetMapping("/RangertVisning")
	public String visRangerteStands(Model model,
			HttpServletRequest request,
			HttpServletResponse response, 
			RedirectAttributes ra) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Jury"))return "redirect:/home";
		
		List<Stand> rangerteStander = standservice.rangerStander();
		
		model.addAttribute("standliste", rangerteStander);
		
		return "rangert";
	}
	
}
