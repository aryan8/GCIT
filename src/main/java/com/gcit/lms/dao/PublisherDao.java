/**
 * Author Aryan
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.Publisher;

/**
 * @author Aryan
 *
 */
public class PublisherDao extends BaseDAO implements ResultSetExtractor<List<Publisher>>{


	public void addPublisher(Publisher publisher) throws SQLException{
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[]{publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}
	public Integer addPublisherWithID(Publisher publisher) throws SQLException {


		final String publisherName = publisher.getPublisherName();
		final String publisherAddress = publisher.getPublisherAddress();
		final String publisherPhone = publisher.getPublisherPhone();

		final String sql = "insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "publisherId" });
				ps.setObject(1, publisherName);
				ps.setObject(2, publisherAddress);
				ps.setObject(3, publisherPhone);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();

	}

	public List<Publisher> readAllPublisherByBook(Integer book) throws SQLException{
		return template.query("select * from tbl_publisher where publisherId IN (select pubId from tbl_book where bookId = ?)",
				new Object[] {book}, this);
	

	}	

	public void updatePublisher(Publisher publisher) throws SQLException{
		template.update("update tbl_publisher set publisherName = ?,  publisherAddress = ?,  publisherPhone = ? where publisherId = ?", new Object[]{publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone(), publisher.getPublisherId()});
	}


	public void deletePublisher(Publisher publisher) throws SQLException{
		template.update("delete from tbl_publisher where publisherId = ?", new Object[]{publisher.getPublisherId()});
	}

	public List<Publisher> readAllPublisher() throws SQLException{
		return template.query("select * from tbl_publisher", this);
	}
	public Publisher readByPK(Publisher publisher) throws SQLException{
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[]{publisher.getPublisherId()},this);
		if(publishers != null){
			return publishers.get(0);
		}
		return null;

	}





	@Override
	public  List<Publisher> extractData(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();
		try {
			while(rs.next()){
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherPhone(rs.getString("publisherPhone"));
				publishers.add(p);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publishers;
	}


}
