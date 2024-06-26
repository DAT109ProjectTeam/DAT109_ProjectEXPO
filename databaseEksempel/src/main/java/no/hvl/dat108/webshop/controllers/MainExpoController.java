package no.hvl.dat108.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.repos.StandRepo;
import no.hvl.dat108.webshop.services.StandService;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class MainExpoController {

	@Autowired private StandService ss;
	
	private static RolleUtil rolleutil = new RolleUtil();
	private static BrukerUtil brukerutil = new BrukerUtil();
	
	@GetMapping("/home")
    public String showHome(Model model, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		RedirectAttributes ra) {
        
		brukerutil.sjekkBruker(request, response, model);
		rolleutil.sjekkRolle(request, response, model);
		
        return "hovedside";
    }
	
	@PostMapping("/home")
	public String postHome(Model model) {
		
		return "redirect:home";
	}
	
	@GetMapping("/")
	public String getEmpty(Model model) {
		
		return "redirect:home";
	}
	
	@PostMapping("/")
	public String postEmpty(Model model) {
		
		return "redirect:home";
	}
	
}
