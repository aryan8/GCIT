package com.gcit.lms;

import java.util.Locale;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	

	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/response", method = RequestMethod.GET)
	public String response(Model model) {
		return "response";
	}
	
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	//public String admin(Locale locale, Model model) {
	//	return "adminoptions";
	//}
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	//public String librarian(Locale locale, Model model) {
	//	return "librarian";
	//}

}
