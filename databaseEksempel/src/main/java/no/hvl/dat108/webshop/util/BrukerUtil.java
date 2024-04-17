package no.hvl.dat108.webshop.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BrukerUtil {

	private static int nr;

	public static int cookieTimer = 60;

	public BrukerUtil() {
		nr = 0;
	}

	private boolean sjekkOmNyBruker(HttpServletRequest request, HttpServletResponse response, Model model) {

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("ID") && !(getBrukerId(request) == -1)) {
					model.addAttribute("ID", cookie.getValue());
					return false;
				}
			}
		}
		return true;
	}

	public void sjekkBruker(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (sjekkOmNyBruker(request, response, model)) {
			nr++;
			String cookieValue = "" + nr;
			Cookie cookie;
			cookie = new Cookie("ID", cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(cookieTimer);
			response.addCookie(cookie);
			model.addAttribute("ID", "" + nr);
		}
	}

	public int getBrukerId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		int id = -1;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("ID")) {
					String string = cookie.getValue();
					return Integer.parseInt(string);
				}
			}
		}

		return id;
	}

	public double modifyIdCookieDuration(double lengde) {
		double varighet = lengde * 60 * 60;

		cookieTimer = (int)varighet;
		return lengde;
	}

}
