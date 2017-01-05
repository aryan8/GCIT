/**
 * Author Aryan
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loans;

/**
 * @author Aryan
 *
 */
public class LoanDao extends BaseDAO implements ResultSetExtractor<List<Loans>> {

	@Autowired
	BookDao bdao;
	
	@Autowired
	BranchDao brdao;
	
	@Autowired
	BorrowerDao bodao;
	
	public void addLoan(Loans loans) throws SQLException{
		template.update("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)", new Object[]{loans.getBook().getBookId(),loans.getBranches().getBranchId(),loans.getBorrower().getCardNo(),loans.getDayOut(),loans.getDueDate(),loans.getDateIn()});
	}

	public void updateLoan(Loans loans) throws SQLException{
		template.update("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?", new Object[]{loans.getDayOut(), loans.getBook().getBookId(),loans.getBranches().getBranchId(),loans.getBorrower().getCardNo()});
	}
	//
	public Loans readAllLaonsByBook(Integer book) throws SQLException{
		 
				List<Loans> loans = template.query("select * from tbl_book_loans where bookId = ?",
				new Object[] {book}, this);
				if(loans != null){
				return loans.get(0);
				}
				return null;
	}



	public void deleteLoan(Loans loans) throws SQLException{
		System.out.println("Aryanaaaaa:"+loans.toString());
		
		template.update("delete from tbl_book_loans where bookId =?  and cardNo =? and branchId =?", new Object[]{loans.getBook().getBookId(),loans.getBorrower().getCardNo(), loans.getBranches().getBranchId()});
	}

	public List<Loans> readAllLaons() throws SQLException{
		return template.query("select * from tbl_bok_loans", this);
	}
	public List<Loans> readBookByBorrower(Loans loans) throws SQLException{
		return template.query("select B.bookId , B.title from tbl_book_loans L inner join tbl_library_branch BL on BL.branchId=L.branchId inner join tbl_book B on B.bookId=L.bookId  where L.branchId = ? and L.cardNo = ?", new Object[]{loans.getBranches().getBranchId(), loans.getBorrower().getCardNo()},this);

	}


	@Override
	public  List<Loans> extractData(ResultSet rs) {
		List<Loans> loans = new ArrayList<Loans>();
	
		try {
			while(rs.next()){
				Loans l = new Loans();
				l.setDayOut(rs.getDate("dateOut"));
				l.setDueDate(rs.getDate("dueDate"));
				l.setDateIn(rs.getDate("dateIn"));
	
				loans.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loans;
	}



}
