package fil.iagl.iir.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.outils.FeedMeAuthentificationToken;

@Controller
public class AccueilController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "/index.html";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String displayForm() {
		return "/login.html";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String login(@RequestBody Authentification authentification) {

		SecurityContextHolder.getContext().setAuthentication(new FeedMeAuthentificationToken(authentification));

		return null;
	}

}
