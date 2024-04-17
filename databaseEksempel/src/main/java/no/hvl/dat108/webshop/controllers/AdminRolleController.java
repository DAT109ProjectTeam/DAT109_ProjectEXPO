package no.hvl.dat108.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class AdminRolleController {

	@Autowired private BrukerUtil brukeridutil;
	
	@Autowired private RolleUtil rolleutil;
	
	@Autowired private StandService ss;
	
	public static boolean ErEventetAktivt = false;
	
	@GetMapping("/Admin")
	public String getAdminRolleController(Model model, 
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra) {

		return "adminpassord";
	}
	
	@PostMapping("/Admin")
	public String postAdminRolleController(Model model, 
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra,
			@RequestParam String passord) {
		
		brukeridutil.sjekkBruker(request, response, model);
		rolleutil.sjekkRolle(request, response, model);
		
		if(!passord.equals("3647")) {
			ra.addFlashAttribute("feilmelding", "Feil passord");
			return "redirect:/home";
		}
		
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
			cookieValue = "Admin";

			// Update the cookie
			Cookie cookie = new Cookie("Rolle", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(RolleUtil.cookieTimer);
			response.addCookie(cookie);
			
		}
	
		return "redirect:/home";
	}
	
	@GetMapping("/AdminSide")
	public String getAdminSide(Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding","Du er ikke en Admin");
			return "redirect:/home";
		}
		
		model.addAttribute("status", ErEventetAktivt);
		
		return "adminside";
	}
	
	@GetMapping("/reset")
	public String reset(Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding","Du er ikke en Admin");
			return "redirect:/home";
		}
		
		model.addAttribute("feilmelding", "Databasen ble reset");
		
		ss.resetDatabase();
		
		return "redirect:home";
	}
	
	@GetMapping("/StartEvent")
	public String getStartEvent(Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding","Du er ikke en Admin");
			return "redirect:/home";
		}
		
		ErEventetAktivt = true;
		ra.addFlashAttribute("feilmelding", "Eventet er startet");
		
		return "redirect:home";
	}
	
	@GetMapping("/StoppEvent")
	public String getStoppEvent(Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding","Du er ikke en Admin");
			return "redirect:/home";
		}
		
		ErEventetAktivt = false;
		ra.addFlashAttribute("feilmelding", "Eventet er stoppet");
		
		return "redirect:home";
	}
	
	@PostMapping("/ChangeCookieDuration")
	public String changeCOokieDuration(Model model,
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes ra,
			@RequestParam String lengde) {
		
		brukeridutil.sjekkBruker(request, response, model);
		String rolle = rolleutil.sjekkRolle(request, response, model);
		
		if(!rolle.equals("Admin")) {
			ra.addFlashAttribute("feilmelding","Du er ikke en Admin");
			return "redirect:/home";
		}
		
		brukeridutil.modifyIdCookieDuration(Double.parseDouble(lengde));
		rolleutil.modifyRolleCookieDuration(Double.parseDouble(lengde));
		
		ra.addFlashAttribute("feilmelding", "Cookies lengde er nå: " + lengde + " timer");
		
		return "redirect:home";
	}
	
}
