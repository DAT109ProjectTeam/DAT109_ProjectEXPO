package no.hvl.dat108.webshop.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RolleUtil {
	
	public static int cookieTimer = 20;
	
	public String sjekkRolle(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		
		if(cookies != null) {
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Rolle")) {
					cookieValue = cookie.getValue();
					model.addAttribute("Rolle", cookieValue);
					return cookieValue;
				}
			}
		}
		
        if (cookieValue == null) {
            String rolleValue = "Tilskuer";
            Cookie rolleCookie = new Cookie("Rolle", rolleValue);
            rolleCookie.setPath("/");
            rolleCookie.setMaxAge(cookieTimer);
            response.addCookie(rolleCookie);
            model.addAttribute("Rolle", rolleCookie.getValue());
            return rolleCookie.getValue();
        }
        return cookieValue;
	}
	
	public double modifyRolleCookieDuration(double lengde) {
		double varighet = lengde * 60 * 60;

		cookieTimer = (int)varighet;
		return lengde;
    }
	
}
