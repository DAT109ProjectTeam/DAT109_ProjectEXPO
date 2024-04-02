package no.hvl.dat108.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.services.StandService;

@Controller
public class JuryRolleController {

	@Autowired private StandService standservice;
	
	@GetMapping("/Jury")
	public String getJuryRolleController(
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response, 
			RedirectAttributes ra) {

		Cookie[] cookies = request.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Rolle")) {
					cookieValue = cookie.getValue();
					break;
				}
			}

			// Modify the cookie value
			cookieValue = "Jury";

			// Update the cookie
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
			RedirectAttributes ra) {
		
		List<Stand> rangerteStander = standservice.rangerStander();
		
		model.addAttribute("standliste", rangerteStander);
		
		return "rangert";
	}
	
}
