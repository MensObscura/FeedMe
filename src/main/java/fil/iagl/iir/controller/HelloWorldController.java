package fil.iagl.iir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class HelloWorldController {

	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "HelloWorld!";
	}
	
	
}
