/**
 * borrower Aryan
 */
package com.gcit.lms.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.Borrower;

import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * @borrower Aryan
 *
 */

public class BorrowerDao extends BaseDAO implements ResultSetExtractor<List<Borrower>>{

	/**
	 * @param conn
	 */
	
		public void addBorrower(Borrower borrower) throws SQLException{
		template.update("insert into tbl_borrower (name, address, phone) values (?,?,?)", new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException{
		template.update("update tbl_borrower set name = ?, address =?, phone = ? where cardNo = ?", new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}
	
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		template.update("delete from tbl_borrower where cardNo = ?", new Object[]{borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws SQLException{
		return template.query("select * from tbl_borrower", this);
	}
	public Borrower cardValidation(Integer cardNo) throws SQLException{
		System.out.println("cardNo3:"+cardNo);
		List<Borrower> card = template.query("select * from tbl_borrower where cardNo = ?", new Object[]{cardNo},this);
		
		if(card != null && card.size() > 0){
			return card.get(0);
		}
		return null ;
	}
	public Borrower readByPK(Borrower borrower) throws SQLException{
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo = ?", new Object[]{borrower.getCardNo()},this);
		if(borrowers != null){
			return borrowers.get(0);
		}
		return null;
		
	}
	

	
	@Override
	public  List<Borrower> extractData(ResultSet rs) {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		try {
				while(rs. next()){
				Borrower b = new Borrower();
				b.setCardNo(rs.getInt("cardNo"));
				b.setName(rs.getString("name"));
				b.setAddress(rs.getString("address"));
				b.setPhone(rs.getString("phone"));
				borrowers.add(b);
				System.out.println("Id:"+ b.getCardNo());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowers;
	}

	
 
}
