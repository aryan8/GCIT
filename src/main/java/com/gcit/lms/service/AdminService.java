package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.AuthorDao;
//import com.gcit.lms.dao.BookCopyDAO;
//import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookDao;
//import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BorrowerDao;
//import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.BranchDao;
import com.gcit.lms.dao.CopiesDao;
import com.gcit.lms.dao.GenreDao;
//import com.gcit.lms.dao.GenreDAO;
//import com.gcit.lms.dao.LoanDAO;
import com.gcit.lms.dao.LoanDao;
//import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.dao.PublisherDao;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	@Autowired
	AuthorDao adao;

	@Autowired
	BookDao bdao;

	@Autowired
	PublisherDao pdao;

	@Autowired
	BranchDao brdao;

	@Autowired
	BorrowerDao bodao;

	@Autowired
	CopiesDao cdao;

	@Autowired
	LoanDao ldao;
	
	@Autowired
	GenreDao gdao;

	@Transactional
	public void addAuthor(String authorName) throws SQLException {
		Author author= new Author();
		author.setAuthorName(authorName);
		adao.addAuthor(author);
		// adao.addwithID
		// loop thri the book list
		// add to bookauthordao
	}

	//	@Transactional
	//	public void addBook(Book book) throws SQLException {
	//		Integer bookId = bdao.addBookWithID(book);
	//		for (Author a : book.getAuthors()) {
	//			// bdao.addBookAuthors(book.getBookId(), a.getAuthorId());
	//		}
	//	}


	//public Author readAuthorByPK(Author author) throws SQLException {
	//		return adao.readAuthorByPK(author);
	//}

	public void updateAuthor(Integer authorId, String authorName) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		adao.updateAuthor(author);
	}

	//public Integer getAuthorsCount() throws SQLException {
	//		return adao.getAuthorsCount();
	//	}
	
	private void fillBook(Book book) {
		try {
			book.setGenre(readBooksGenres(book.getBookId()));
			book.setPublisher(readBooksPublisher(book.getBookId()));
			book.setAuthors(readAllBooksAuthors(book.getBookId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//Book//
//	public List<Book> readAllBooks() throws Exception {
//		List<Book> books = bdao.readAllBooks();	
//
//		for(Book book: books) {
//			this.fillBook(book);
//		}
//		
//		return books;
//	}
	public List<Book> readAllBooks(String searchString) throws Exception {
		List<Book> books = bdao.readAllBooks(searchString);	
		for(Book book: books) {
			this.fillBook(book);
		}
		return books;
	}
	
	public List<Author> readAllBooksAuthors(Integer bookId) throws SQLException{
		List<Author> authors = adao.readAllAuthorsByBook(bookId);
		return authors;
	}
	
	public Publisher readBooksPublisher(Integer bookId) throws SQLException{
		List<Publisher> publishers =  pdao.readAllPublisherByBook(bookId);
		
		if(publishers != null && !publishers.isEmpty()) {
			return publishers.get(0);
		}
		
		return null;
	}
	
	public List<Genre> readBooksGenres(Integer bookId) throws SQLException{
		List<Genre> genres = gdao.readAllGenresByBook(bookId);
		return genres;
	}
	
	
	public Book readBookByPK(Integer bookId) throws SQLException{
		Book book = new Book();
		book.setBookId(bookId);
		return bdao.readByPK(book);
	}

	@Transactional
	public void addBook(String title, Integer publisherId, Integer[] authorIds, Integer[] genreIds) throws Exception, SQLException {
		Book book = new Book();
		book.setTitle(title);
		System.out.println("Author: "+ authorIds);
		List<Author> authors = new ArrayList<Author>();
		for(Integer authorId: authorIds){
			Author author = new Author();
			author.setAuthorId(authorId);
			authors.add(author);

		}
		book.setAuthors(authors);

		
		List<Genre> genres = new ArrayList<Genre>();
		for(Integer genreId:genreIds){
			Genre genre = new Genre();
			genre.setGenreId(genreId);
			genres.add(genre);
		}
		book.setGenre(genres);

		//(publisher)
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		book.setPublisher(publisher);
		
		
		bdao.addBook(book);
	}
	
	@Transactional
	public void deleteBook(Integer bookId) throws SQLException{
		Book book = new Book();
		book.setBookId(bookId);
		bdao.deleteBook(book);
	}
	
	@Transactional
	public void updateBook(Integer bookId, String title, Integer[] authorIDs, Integer[] genreIDs, Integer publisherId) throws SQLException {
	Book book = new Book();
	book.setBookId(bookId);
	book.setTitle(title);
	Publisher publisher = new Publisher();
	publisher.setPublisherId(publisherId);
	book.setPublisher(publisher);
	List<Author> authors = new ArrayList<Author>();
	for(Integer aId : authorIDs){
		Author author = new Author();
		author.setAuthorId(aId);
		authors.add(author);
	}
	book.setAuthors(authors);
	
	List<Genre> genres = new ArrayList<Genre>();
	for(Integer gId : genreIDs){
		Genre genre = new Genre();
		genre.setGenreId(gId);
		genres.add(genre);
	}
	book.setGenre(genres);
	bdao.updateBook(bookId ,title, authorIDs, genreIDs, publisherId);
	
	}

	
	//Author
	public List<Author> readAllAuthors(Integer pageNo, String searchString) throws SQLException{
		List<Author> authors = adao.readAllAuthors(pageNo, searchString);
				return authors;
	}
	public List<Author> readAllAuthors(Integer pageNo) throws SQLException{
		List<Author> authors = adao.readAllAuthors(pageNo, null);
				return authors;
	}
	public List<Author> readAllAuthorsWithOutPage() throws SQLException{
		List<Author> authors = adao.readAllAuthors();
				return authors;
	}
	@Transactional
	public void deleteAuthor(Integer authorId) throws SQLException{
		Author author = new Author();
		author.setAuthorId(authorId);
		adao.deleteAuthor(author);
	}
	
	public Author readAuthorByPK(Integer authorId) throws SQLException{
		Author author = new Author();
		author.setAuthorId(authorId);
		return adao.readByPK(authorId);
	}
	public Integer getAuthorsCount(String searchStr) throws SQLException{
		return adao.getFetchSize();
	}

	//Genre
	public List<Genre> readAllGenre() throws SQLException{
		List<Genre> genres = gdao.readAllGenres();
		return genres;
	}
	
	public Genre readGenreByPK(Integer genreId) throws SQLException{
		
		return gdao.readByPK(genreId);
	}


	//Branch
	public List<Branch> readBranch() throws SQLException{
		List<Branch> branches = brdao.readAllBranch();
		return branches;
	}

	public Branch readBranchByPK(Integer branchId) throws SQLException{
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		return brdao.readByPK(branchId);
	}

	@Transactional
	public void addBranch(String branchName, String branchAddress) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddr(branchAddress);
		brdao.addBranch(branch);
	}

	@Transactional
	public void updateBranch(Integer branchId, String branchName, String branchAddress) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName);
		branch.setBranchAddr(branchAddress);
		brdao.updateBranch(branch);
	}

	@Transactional
	public void deleteBranch(Integer branchId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		brdao.deleteBranch(branch);

	}


	//Borrower
	public List<Borrower> readBorrower() throws SQLException{
		List<Borrower> borrowers = bodao.readAllBorrowers();
		return borrowers;
	}
	public Borrower readBorrowerByPK(Integer cardNo) throws SQLException{
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		return bodao.readByPK(borrower);
	}
	@Transactional
	public void addBorrower(String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);
		bodao.addBorrower(borrower);
	}
	@Transactional
	public void deleteBorrower(Integer cardNo) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		bodao.deleteBorrower(borrower);
	}
	@Transactional
	public void updateBorrower(Integer cardNo, String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);
		bodao.updateBorrower(borrower);
	}

	//Publisher
	public List<Publisher> readPublisher() throws SQLException{
		List<Publisher> publishers=pdao.readAllPublisher();
		return publishers;
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException{
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		return pdao.readByPK(publisher);
	}
	
	@Transactional
	public void addPublisher(String publisherName, String publisherAddress,String publisherPhone) throws SQLException{
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		pdao.addPublisher(publisher);
	}
	
	@Transactional
	public void deletePublisher(Integer publisherId) throws SQLException{
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		pdao.deletePublisher(publisher);
	}
	
	@Transactional
	public void updatePublisher(Integer publisherId, String publisherName, String publisherAddress,String publisherPhone) throws SQLException{
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		pdao.updatePublisher(publisher);
	}
}
