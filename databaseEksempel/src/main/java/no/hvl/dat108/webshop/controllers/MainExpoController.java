package no.hvl.dat108.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class MainExpoController {

	private static RolleUtil rolleutil = new RolleUtil();
	private static BrukerUtil brukerutil = new BrukerUtil();
	
	@GetMapping("/home")
    public String showHome(Model model, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		RedirectAttributes ra,
    		Session session) {
        
		brukerutil.sjekkBruker(request, response, model);
		rolleutil.sjekkRolle(request, response, model);
		
        return "hovedside";
    }
}
