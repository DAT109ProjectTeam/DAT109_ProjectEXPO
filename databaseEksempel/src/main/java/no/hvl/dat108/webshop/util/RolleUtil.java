package no.hvl.dat108.webshop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RolleUtil {
	
	private int cookieTimer = 60;
	
	public void sjekkRolle(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		
		if(cookies != null) {
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Rolle")) {
					cookieValue = cookie.getValue();
					model.addAttribute("Rolle", cookieValue);
					break;
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
        }
	}
	
}
