package no.hvl.dat108.webshop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
					model.addAttribute("ID", cookie.getValue());
					return false;
				}
			}
		}
		return true;
	}
	
	public void sjekkBruker(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(sjekkOmNyBruker(request, response, model)){
			this.nr++;
			String cookieValue = "" + this.nr;
			Cookie cookie;
			cookie = new Cookie("ID", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(cookieTimer);
			response.addCookie(cookie);
			model.addAttribute("ID", ""+nr);
		}
	}
	
	public int getBrukerId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		int id = -1;

		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("ID")) {
					String string = cookie.getValue();
					return Integer.parseInt(string);
				}
			}
		}
		
		return id;
	}
	
}
