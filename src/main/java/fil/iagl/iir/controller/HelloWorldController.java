package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fil.iagl.iir.service.UtilisateurService;

@RequestMapping("/test")
@Controller
public class HelloWorldController {

	@Autowired
	private UtilisateurService utilisateurService;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return utilisateurService.getAll().toString();
	}

}
