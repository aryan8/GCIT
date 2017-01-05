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
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;
/**
 * @author Aryan
 *
 */
public class CopiesDao extends BaseDAO implements ResultSetExtractor<List<Copies>> {

	@Autowired
	BranchDao brdao;
	@Autowired
	BookDao bdao;
	
	
	public void addCopies(Copies copies, Book book, Branch branch) throws SQLException{
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)", new Object[]{copies.getBooks().getBookId(),copies.getBranchs().getBranchId(),copies.getNoCopies()});
	}
	
	public void updateCopies(Copies copies) throws SQLException{
		template.update("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", new Object[]{copies.getNoCopies(), copies.getBooks().getBookId(), copies.getBranchs().getBranchId()});
	}
	
	
	public void deleteCopies(Copies copies) throws SQLException{
		template.update("delete from tbl_book_copies where bookId = ? and  branchId = ?)", new Object[]{copies.getBooks().getBookId(),copies.getBranchs().getBranchId(),copies.getNoCopies()});
	}
	
	public List<Copies> readAllCopies() throws SQLException{
		return template.query("select * tbl_book_copies",this);
	}
	public Copies getNoOfCopies(Copies copy) throws SQLException{
		List<Copies> copies = template.query("select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?", new Object[]{copy.getBooks().getBookId(), copy.getBranchs().getBranchId()},this);
		if(copies != null && !copies.isEmpty()){
			return copies.get(0);
		}
		return null;
	}
	public List<Copies> readBranchBook(Copies copies) throws SQLException{
		
		return template.query("select * from tbl_book_copies where branchId = ?" , new Object[]{copies.getBranchs().getBranchId()},this);
	}
	
	@Override
	public  List<Copies> extractData(ResultSet rs) {
		List<Copies> copies= new ArrayList<Copies>();
	
		try {
			while(rs.next()){
			Copies c= new Copies();
			c.setNoCopies(rs.getInt("noOfCopies"));
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book = bdao.readByPK(book);
			Branch branch = brdao.readByPK(rs.getInt("branchId"));
			c.setBooks(book);
			c.setBranchs(branch);
			copies.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	}

	

}
