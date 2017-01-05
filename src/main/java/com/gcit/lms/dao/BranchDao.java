/**
 * branch Aryan
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loans;

/**
 * @branch Aryan
 *
 */
@Component
public class BranchDao extends BaseDAO implements ResultSetExtractor<List<Branch>> {

	@Autowired
	LoanDao ldao;
	
	//public BranchDao(LoanDao ld, JdbcTemplate temp)
	//{
	//	ldao = ld;
	//	template= temp;
	//}

	public void addBranch(Branch branches) throws SQLException{
		template.update("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[]{branches.getBranchName(),branches.getBranchAddr()});
	}

	public void updateBranch(Branch branches) throws SQLException{
		template.update("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[]{branches.getBranchName(),  branches.getBranchAddr(),branches.getBranchId()});
	}


	public void deleteBranch(Branch branches) throws SQLException{
		template.update("delete from tbl_library_branch where branchId = ?", new Object[]{branches.getBranchId()});
	}

	public List<Branch> readAllBranch() throws SQLException{
		//if (template == null)
			
			System.out.println("KHAR");
		return template.query("select * from tbl_library_branch", this);
	}
	public Branch readByPK(Integer branch) throws SQLException{
		List<Branch> branchs = template.query("select * from tbl_library_branch where branchId = ?", new Object[]{branch},this);
		if(branchs != null){
			return branchs.get(0);
		}
		return null;

	}
	public List<Branch> readByCardPK(Integer cardNo) throws SQLException{
		List<Branch> branchs = template.query("select * from tbl_library_branch where branchId IN (select branchId from tbl_book_loans where cardNo = ?", new Object[]{cardNo},this);
		if(branchs != null){
			return  branchs;
		}
		return null;

	}



	@Override
	public List<Branch> extractData(ResultSet rs) {
		List<Branch> branches = new ArrayList<Branch>();

		try {
			while(rs.next()){
				Branch b= new Branch();
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setBranchAddr(rs.getString("branchAddress"));
				branches.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branches;
	}


}
