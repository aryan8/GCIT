/**
 * Author Aryan
 */
package com.gcit.lms.service;

/**
 * @author Aryan
 *
 */
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.BranchDao;
import com.gcit.lms.dao.CopiesDao;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Configurable
public class LibrarianService {
	@Autowired
	BranchDao brdao;
	@Autowired
	CopiesDao cdao;
	
	//public LibrarianService(BranchDao brd, CopiesDao cd)
	//{
	//	brdao = brd;
	//	cdao = cd;
	//}
	
	public List<Branch> readBranch() throws SQLException{
		//if (brdao == null)
		//	brdao = new BranchDao();
		
		List<Branch> branches = brdao.readAllBranch();
		//System.out.println("Branch:"+ branches);
		return branches;
	}
	public Branch readBranchByPK(Integer branchId) throws SQLException{
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch= brdao.readByPK(branchId);
		return branch;
	}
	
	@Transactional

	public void updateBranch(Integer branchId, String branchName, String branchAddress) throws SQLException{
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName);
		branch.setBranchAddr(branchAddress);
		brdao.updateBranch(branch);
	}
	
	public List<Copies> showBranchBook(Copies copies)throws SQLException{
		List<Copies> copies1= cdao.readBranchBook(copies);
		return copies1;
	}
	public Copies branchBookPK (Copies copie) throws SQLException {
		return cdao.getNoOfCopies(copie);
	}
	
	@Transactional

	public void updateCopies(Integer branchId, Integer bookId, Integer noOfCopies) throws SQLException{
		Book book =new Book();
		book.setBookId(bookId);
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		Copies copies= new Copies();
		copies.setBooks(book);
		copies.setBranchs(branch);
		copies.setNoCopies(noOfCopies);
		cdao.updateCopies(copies);
	}

	
}

