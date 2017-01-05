/**
 * Author Aryan
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookDao;
import com.gcit.lms.dao.BorrowerDao;
import com.gcit.lms.dao.BranchDao;
import com.gcit.lms.dao.LoanDao;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loans;

/**
 * @author Aryan
 *
 */
public class BorrowerService {
	@Autowired
	BranchDao brdao;

	@Autowired
	BookDao bdao;

	@Autowired
	LoanDao ldao;

	@Autowired
	BorrowerDao bodao;

	public Borrower cardValidation(Integer cardNo){
		
		System.out.println("cardNo:"+cardNo);

		try {
			return bodao.cardValidation(cardNo);
		} catch (SQLException e) {
			return null;

		}
	}
	public void showAllBranches (Integer cardNo) throws SQLException{
		brdao.readByCardPK(cardNo);	
	}
	//
	public Loans readAllBooksLoans(Integer bookId) throws SQLException{
		Loans loans =ldao.readAllLaonsByBook(bookId);
		return loans;
	}
	
	public List<Branch> readBranch() throws SQLException{
		return brdao.readAllBranch();

	}
	
	public Branch readBranchByPK(Integer branch) throws SQLException{
		return brdao.readByPK(branch);
	}
	
	public List<Book> showBranchBook(Integer branchId, Integer cardNo)throws SQLException{
		return bdao.readBranchBookByCopies(branchId, cardNo);
	}
	
	public List<Book> showBranchBookReturn(Integer branchId , Integer cardNo) throws SQLException{
		return bdao.showBorrowedBook(branchId, cardNo);
	}
	
	public Book selectBook(Book book) throws SQLException{
		return bdao.readByPK(book);
	}
	
	@Transactional
	public void updateBookLoans(Integer branchId, Integer bookId, Integer cardNo) throws SQLException{
		Borrower borrower = new Borrower();
		Book book = new Book();
		Branch branch  = new Branch();
		borrower.setCardNo(cardNo);
		book.setBookId(bookId);
		branch.setBranchId(branchId);
		Loans loan = new Loans();
		loan.setBook(book);
		loan.setBorrower(borrower);
		loan.setBranches(branch);
		Date date = new Date(System.currentTimeMillis());
		loan.setDayOut(date);
		loan.setDueDate(new Date(System.currentTimeMillis() + 7*24*60*60*1000));
		ldao.addLoan(loan);
	}
	
	public List<Loans> shoeBorrowerBook(Loans loans) throws SQLException{
		return ldao.readBookByBorrower(loans);
	}
	
	public void updateReturnBook(Integer branchId, Integer bookId, Integer cardNo) throws SQLException{
		Borrower borrower = new Borrower();
		Book book = new Book();
		Branch branch  = new Branch();
		branch.setBranchId(branchId);
		book.setBookId(bookId);
		borrower.setCardNo(cardNo);
		branch.setBranchId(branchId);
		Loans loan = new Loans();
		loan.setBook(book);
		loan.setBorrower(borrower);
		loan.setBranches(branch);	
		ldao.deleteLoan(loan);
	
	}

}
