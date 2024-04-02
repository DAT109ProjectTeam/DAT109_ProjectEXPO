package no.hvl.dat108.webshop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BrukerUtil {

	private int nr;
	
	private int cookieTimer;
	
	public BrukerUtil() {
		this.nr = 0;
		this.cookieTimer = 20;
	}
	
	private boolean sjekkOmNyBruker(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			
			for(Cookie cookie : cookies) {
				if (cookie.getName().equals("ID")) {
//					System.out.println("sjekkOmNyBruker:  false");
					model.addAttribute("ID", cookie.getValue());
					return false;
				}
			}
		}
//		System.out.println("sjekkOmNyBruker:  true");
		return true;
	}
	
	public void nyBruker(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(sjekkOmNyBruker(request, response, model)){
			this.nr++;
			String cookieValue = "" + this.nr;
			Cookie cookie;
			cookie = new Cookie("ID", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(cookieTimer);
			response.addCookie(cookie);
			model.addAttribute("ID", ""+nr);
//			System.out.println("Ny bruker: " + nr);
		}
	}
	
}
