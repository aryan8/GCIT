/**
 * Author Aryan
 */
package com.gcit.lms;

/**
 * 
 * @author Aryan
 *
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;




@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService service;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String admin(Model model) {
		return "adminoptions";
	}

	/*
	 *
	 * Admin Book controller
	 * 
	 */	
	@RequestMapping(value = "/adminbook", method = RequestMethod.GET)
	public String showBooks(Model model) {
		return "adminbook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBook(Model model) {
		return "addbook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBook(Model model,  @RequestParam("title") String title, @RequestParam(value="publisherId") Integer publisherId,
			@RequestParam(value="authorId[]")Integer[] authorIds, @RequestParam(value="genre_ids[]") Integer[] genreIds) throws Exception{


		service.addBook(title, publisherId ,authorIds,genreIds);


		model.addAttribute("message", "The transaction has been successfully processed."); 
		return "adminbook";

	}
	@RequestMapping(value = "/deletebook", method = RequestMethod.GET)
	public String deleteBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		service.deleteBook(bookId);
		model.addAttribute("message", "The transaction has been successfully processed."); 
		return "adminbook";
	}

	@RequestMapping(value = "/updatebook", method = RequestMethod.GET)
	public String updateBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		Book book = service.readBookByPK(bookId);
		model.addAttribute("book",  book);
		return "editbook";

	}

	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public String updateBook(Model model, @RequestParam("bookId") Integer bookId , @RequestParam("title") String title, @RequestParam("publisherId") Integer publisherId,
			@RequestParam(value="authorIds[]", required=false) Integer[] authorIds, @RequestParam(value="genreIds[]", required=false) Integer[] genreIds) throws SQLException{
		service.updateBook(bookId, title, authorIds, genreIds, publisherId);
		model.addAttribute("message", "The transaction has been successfully processed."); 
		return "adminbook";
	}	
	@RequestMapping(value = "/searchbooks", method = RequestMethod.GET)
	@ResponseBody
	private String searchBookAjax(@RequestParam("searchString") String searchString) throws Exception {
		
		searchString = searchString.trim();
		List<Book> books = new ArrayList<Book>();
		try {
			books = service.readAllBooks(searchString);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder str = new StringBuilder();
		str.append("<table class='table'>");
		str.append("<tr><th>#</th><th>Book Name</th><th>Author Name</th><th>Genre</th><th>Publisher</th><th></th><th></th></tr>");
		for (Book b : books) {
			str.append("<tr><td>" + (books.indexOf(b) + 1) + "</td>");
			str.append("<td>" + b.getTitle() + "</td>");
			
			
			if(b.getAuthors() != null && !b.getAuthors().isEmpty()) {
				str.append("<td>");
				for (int i = 0; i < b.getAuthors().size(); i++){		
					str.append(b.getAuthors().get(i).getAuthorName()+ " - ");
				}
				str.append("</td>");
			} else {
				str.append("<td></td>");
			}

			if(b.getGenres() != null && !b.getGenres().isEmpty()) {
				str.append("<td>");
				for (int i = 0; i < b.getGenres().size(); i++) {
					str.append(b.getGenres().get(i).getGenreName()+ " - ");
				}
				str.append("</td>");
			} else {
				str.append("<td></td>");
			}
			
			if(b.getPublisher() != null) {
				str.append("<td>"+b.getPublisher().getPublisherName()+"</td>");
			} else {
				str.append("<td></td>");
			}
			
			str.append(
					"<td><a class='btn btn-success' data-toggle='modal' data-target='#editauthormodal' onclick='getBookDetails("
							+ b.getBookId() + ")'>Edit</a></td>");
			str.append("<td><a class='btn btn-danger' data-toggle='modal' href='/lms/admin/deletebook?bookId="+  b.getBookId() +"'>	Delete</a></td>");
		}
		str.append("</table>");
		return str.toString();
	}
//	


	/*
	 *
	 * Admin Author controller
	 * 
	 */	
	@RequestMapping(value = "/adminauthor", method = RequestMethod.GET)
	public String showAuthors(Model model) {
		return "adminauthor";
	}
	
	@RequestMapping(value = "/searchauthors", method = RequestMethod.GET)
	@ResponseBody
	private String searchAuthorAjax(@RequestParam("searchString") String searchString, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		
		searchString = searchString.trim();
		List<Author> authors1 = new ArrayList<Author>();
		try {
			authors1 = service.readAllAuthors(pageNo, searchString);
			System.out.println("Authors:"+authors1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder str = new StringBuilder();
		str.append("<table class='table'>");
		str.append("<tr><th>#</th><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr>");
		for (Author a : authors1) {
			str.append("<tr><td>" + (authors1.indexOf(a) + 1) + "</td>");
			str.append("<td>" + a.getAuthorName() + "</td>");
			str.append(
					"<td><a class='btn btn-success' data-toggle='modal' data-target='#editauthormodal' href='editauthor?authorId="
							+ a.getAuthorId() + "'>Edit</a></td>");
			str.append("<td><a class='btn btn-danger' data-toggle='modal' href='/lms/admin/deleteauthor?authorId="+ a.getAuthorId() +"'>	Delete</a></td>");
		}
		str.append("</table>");
		str.append("<nav aria-label='Page navigation'>");
		str.append("<ul class='pagination'>");
		str.append("<li><a href='#' aria-label='Previous'> <spanaria-hidden='true'>&laquo;</span></a></li>");
		
		Integer count = service.getAuthorsCount(searchString);
		Integer pages = 1;
		
		System.out.println("Count: " + count);

		if (count % 10 > 0) {
			pages = (count / 10) + 1;
		} else {
			pages = (count / 10);
		}
		
		for (int i = 1; i <= pages; i++) {
			str.append("<li><a onclick='searchAuthors("+i+")'>"+i+"</a></li>");
		}
		str.append("<li><a href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li></ul></nav>");
	
		return str.toString();
	}
	
	@RequestMapping(value = "/deleteauthor", method = RequestMethod.GET)
	public String deleteAuthor(Model model, @RequestParam("authorId") Integer authorId) throws SQLException{
		service.deleteAuthor(authorId);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminauthor"); 
		return "redirect:/response";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.GET)
	public ModelAndView editAuthor(Model model, @RequestParam("authorId") Integer authorId) throws SQLException{
		Author author = service.readAuthorByPK(authorId);
		Map<String, Author> result = new HashMap<String, Author>();
		
		result.put("author", author);
		
		return new ModelAndView("editauthor", result);
	}
	@RequestMapping(value = "/editauthor", method = RequestMethod.POST)
	public String updateAuthor(Model model, @RequestParam("authorId") Integer authorId, @RequestParam("authorName") String authorName) throws SQLException{
		service.updateAuthor(authorId, authorName);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminauthor"); 
		return "redirect:/response";
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
	public String addAuthor(Model model) {
		return "addauthor";
	}
	@RequestMapping(value = "/addauthor", method = RequestMethod.POST)
	public String addAuthor(Model model, @RequestParam("authorName") String authorName) throws SQLException{
		service.addAuthor(authorName);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminauthor"); 
		return "redirect:/response";
	}
	
	

	/*
	 *
	 * Admin Branch controller
	 * 
	 */	


	@RequestMapping(value = "/addbranch", method = RequestMethod.GET)
	public String addBranch(Model model) {
		return "addbranch";
	}

	@RequestMapping(value = "/addbranch", method = RequestMethod.POST)
	public String addBranch(Model model, @RequestParam("branchName") String branchName, @RequestParam("branchAddress") String branchAddress) throws SQLException{
		service.addBranch(branchName, branchAddress);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminbranch"); 
		return "redirect:/response";
	}

	@RequestMapping(value = "/adminbranch", method = RequestMethod.GET)
	public String showBrnaches(Model model) {
		return "adminbranch";
	}

	@RequestMapping(value = "/updatebranch", method = RequestMethod.GET)
	public String editBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		Branch branch = service.readBranchByPK(branchId);
		model.addAttribute("branch", branch);
		return "editbranch";
	}


	@RequestMapping(value = "/updatebranch", method = RequestMethod.POST)
	public String updateBranch(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName,
			@RequestParam("branchAddress") String branchAddress) throws SQLException{
		service.updateBranch(branchId, branchName, branchAddress);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminbranch"); 
		return "redirect:/response";
	}

	@RequestMapping(value = "/deletebranch", method = RequestMethod.GET)
	public String deleteBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		service.deleteBranch(branchId);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminbranch"); 
		return "redirect:/response";
	}



	/*
	 *
	 * Admin Borrower controller
	 * 
	 */

	@RequestMapping(value = "/adminborrower", method = RequestMethod.GET)
	public String showBorrower(Model model) {
		return "adminborrower";
	}
	@RequestMapping(value = "/addborrower", method = RequestMethod.GET)
	public String addBorrower(Model model) {
		return "addborrower";
	}
	@RequestMapping(value = "/addborrower", method = RequestMethod.POST)
	public String addBorrower(Model model, @RequestParam("borrowerName") String borrowerName, 
			@RequestParam("borrowerAddress") String borrowerAddress, @RequestParam("borrowerPhone") String borrowerPhone) throws SQLException{
		service.addBorrower(borrowerName, borrowerAddress, borrowerPhone);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminborrower"); 
		return "redirect:/response";
	}
	@RequestMapping(value = "/editborrower", method = RequestMethod.GET)
	public String updateBorrower(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		Borrower borrower = service.readBorrowerByPK(cardNo);
		model.addAttribute("borrower", borrower);
		return "editborrower";
	}
	@RequestMapping(value = "/editborrower", method = RequestMethod.POST)
	public String updateBorrower(Model model, @RequestParam("cardNo") Integer cardNo, @RequestParam("borrowerName") String borrowerName,
			@RequestParam("borrowerAddress") String borrowerAddress, @RequestParam("borrowerPhone") String borrowerPhone) throws SQLException{
		service.updateBorrower(cardNo, borrowerName, borrowerAddress, borrowerPhone);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminborrower"); 
		return "redirect:/response";	
	}

	@RequestMapping(value = "/deleteborrower", method = RequestMethod.GET)
	public String deleteBorrower(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		service.deleteBorrower(cardNo);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminborrower"); 
		return "redirect:/response";
	}






	/*
	 *
	 * Admin publisher controller
	 * 
	 */


	@RequestMapping(value = "/adminpublisher", method = RequestMethod.GET)
	public String showPublishers(Model model) {
		return "adminpublisher";
	}

	@RequestMapping(value = "/addpublisher", method = RequestMethod.GET)
	public String addPublisher(Model model) {
		return "addpublisher";
	}

	@RequestMapping(value = "/addpublisher", method = RequestMethod.POST)
	public  String addPublisher(Model model, @RequestParam("publisherName") String publisherName, 
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		service.addPublisher(publisherName, publisherAddress, publisherPhone);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminpublisher"); 
		return "redirect:/response";
	}

	@RequestMapping(value = "/deletepublisher", method = RequestMethod.GET)
	public String deletePublisher(Model model, @RequestParam("publisherId") Integer publisherId) throws SQLException{
		service.deletePublisher(publisherId);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminpublisher"); 
		return "redirect:/response";
	}
	@RequestMapping(value = "/updatepublisher", method = RequestMethod.GET)
	public String editPublisher(Model model, @RequestParam("publisherId") Integer publisherId) throws SQLException{
		Publisher publisher = service.readPublisherByPK(publisherId);
		model.addAttribute("publisher", publisher);
		return "editpublisher";
	}

	@RequestMapping(value = "/updatepublisher", method = RequestMethod.POST)
	public String updatePublisher(Model model, @RequestParam("publisherId") Integer publisherId, @RequestParam("publisherName") String publisherName,
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		service.updatePublisher(publisherId, publisherName, publisherAddress, publisherPhone);
		model.addAttribute("message", "The transaction has been successfully processed.");
		model.addAttribute("url", "admin/adminpublisher"); 
		return "redirect:/response";
	}



}	
