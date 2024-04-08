package no.hvl.dat108.webshop.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.dat108.webshop.util.BrukerUtil;
import no.hvl.dat108.webshop.util.RolleUtil;

@Controller
public class AdminRolleController {

	@Autowired private BrukerUtil brukeridutil;
	
	@Autowired private RolleUtil rolleutil;
	
	@GetMapping("/Admin")
	public String getAdminRolleController(Model model, 
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

			// Modify the cookie value
			cookieValue = "Admin";

			// Update the cookie
			Cookie cookie = new Cookie("Rolle", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(20);
			response.addCookie(cookie);
			
		}

		return "redirect:/home";
	}
}
