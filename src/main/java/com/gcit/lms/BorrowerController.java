/**
 * Author Aryan
 */
package com.gcit.lms;
import java.sql.Date;

import java.sql.SQLException;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Copies;
import com.gcit.lms.entity.Loans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.BorrowerService;
/**
 * @author Aryan
 *
 */
@Controller
@RequestMapping("/borrower")
public class BorrowerController {
	@Autowired
	BorrowerService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String cardvalidation(Model model){
		return "cardvalidation";
	}
	
	@RequestMapping(value = "/cardvalidation", method = RequestMethod.POST)
	public String cardValidation(Model model, @RequestParam("cardNo")Integer cardNo){
		Borrower borrower= service.cardValidation(cardNo);
		System.out.println("cardNo121:"+borrower);

		if(borrower != null){
			model.addAttribute("borrower", borrower);
			return"borroweroptions";
		}else
			return "cardvalidation";
	}
	@RequestMapping(value="/viewbranch", method= RequestMethod.GET)
	public String viewBranches(Model model, @RequestParam("cardNo") Integer cardNo){
		model.addAttribute("cardNo", cardNo);
		return "viewbranchcheckout";	
	}
	
	@RequestMapping(value="/viewbooks", method= RequestMethod.GET)
	public String viewBooks(Model model, @RequestParam("cardNo") Integer cardNo,@RequestParam("branchId") Integer branchId){
		System.out.print("ary:"+ cardNo+"_"+branchId);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		return "listofbooks";	
	}
	

	@RequestMapping(value = "/updateloan", method = RequestMethod.POST )
	public String insertBookLoan(Model model,@RequestParam("cardNo") Integer cardNo,
			@RequestParam("bookId") Integer bookId, @RequestParam("branchId") Integer branchId) throws SQLException{
		service.updateBookLoans(branchId, bookId, cardNo);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "borrower/viewbooks?branchId="+branchId+"&cardNo="+cardNo); 
		return "redirect:/response";
	

	}
	
	@RequestMapping(value="/viewbranchreturn", method= RequestMethod.GET)
	public String viewBranchesReturn(Model model, @RequestParam("cardNo") Integer cardNo){
		model.addAttribute("cardNo", cardNo);
		return "borrowerreturnbranch";	
	}
	@RequestMapping(value="/viewreturnbooks", method= RequestMethod.GET)
	public String viewBooksReturn(Model model, @RequestParam("cardNo") Integer cardNo,@RequestParam("branchId") Integer branchId){
		System.out.print("ary:"+ cardNo+"_"+branchId);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		return "listofbookreturn";	
	}
	@RequestMapping(value="/returnBook", method = RequestMethod.POST )
	public String returnBook(Model model,@RequestParam("cardNo") Integer cardNo,
			@RequestParam("bookId") Integer bookId, @RequestParam("branchId") Integer branchId) throws SQLException{
		service.updateReturnBook(branchId, bookId, cardNo);
		return "redirect:viewreturnbooks?branchId="+branchId+"&cardNo="+cardNo;

	}
	

}

