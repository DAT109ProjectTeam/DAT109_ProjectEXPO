package no.hvl.dat108.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminRolleController {

	@GetMapping("/Admin")
	public String getAdminRolleController(Model model, 
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
