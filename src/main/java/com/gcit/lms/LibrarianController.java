/**
 * Author Aryan
 */
package com.gcit.lms;

import java.util.Locale;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gcit.lms.service.LibrarianService;
import com.gcit.lms.entity.Branch;


@Controller
@RequestMapping("/librarian")
public class LibrarianController {
	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);
	@Autowired
	LibrarianService service;
	
	@RequestMapping(value="", method= RequestMethod.GET)
	public String librarian(Model model){
		return "librarian";
		
	}
	@RequestMapping(value="/viewbranch", method= RequestMethod.GET)
	public String viewBranches(Model model){
		System.out.print(service == null);
		return "Librarian/viewbranch";	
	}
	@GetMapping("/editbranch")
	public String editBranch(Model model,  @RequestParam("branchId") Integer branchId ) throws SQLException{
		System.out.println("MJ");
		Branch branch =service.readBranchByPK(branchId);
		model.addAttribute("branch", branch);
		return "updatebranchlib";
	}
	
	@PostMapping("/editbranch")
	public final String updateBranch(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName,
			@RequestParam("branchAddress") String branchAddress) throws SQLException{
		System.out.println(branchId + branchName + branchAddress);
		service.updateBranch(branchId, branchName, branchAddress);
		//return "redirect:/librarian/viewbranch";
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "librarian/viewbranch"); 
		return "redirect:/response";
		//return new ModelAndView("Submitted Successfully.");
	}
	
	@RequestMapping(value="/addbranchcopy", method= RequestMethod.GET)
	public String addCopyBranchLib(Model model,  @RequestParam("branchId") Integer branchId) throws SQLException{
		Branch branch = service.readBranchByPK(branchId);
		model.addAttribute("branch", branch);
		return "Librarian/addbranchcopy";
	}
			
	@RequestMapping(value="/addbranchcopy", method= RequestMethod.POST)
	public String  addCopyBranchLib(Model model,  @RequestParam("branchId") Integer branchId, @RequestParam("bookId") Integer bookId,
			@RequestParam("noOfCopies") Integer noOfCopies) throws SQLException{
		service.updateCopies(branchId, bookId, noOfCopies);
		return "redirect:/librarian/addbranchcopy?branchId="+ branchId ;

	}

}
