/**
 * Author Aryan
 */
package com.gcit.lms.dao;
//Test Commit
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AuthorDao extends BaseDAO implements ResultSetExtractor<List<Author>>{


	private Integer fetchSize;
	
	/**
	 * @return the fetchSize
	 */
	public Integer getFetchSize() {
		return fetchSize;
	}
	/**
	 * @param fetchSize the fetchSize to set
	 */
	public void setFetchSize(Integer fetchSize) {
		this.fetchSize = fetchSize;
	}
	public void addAuthor(Author author) throws SQLException{
		template.update("insert into tbl_author (authorName) values (?)", new Object[]{author.getAuthorName()});
	}
	public Integer addAuthorWithID(Author author) throws SQLException {

		final String authorName = author.getAuthorName();
		final String INSERT_SQL = "insert into tbl_author (authorName) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "authorId" });
				ps.setString(1, authorName);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();

	}


	public void updateAuthor(Author author) throws SQLException{
		template.update("update tbl_author set authorName = ? where authorId = ?", new Object[]{author.getAuthorName(), author.getAuthorId()});
	}


	public void deleteAuthor(Author author) throws SQLException{
		template.update("delete from tbl_author where authorId = ?", new Object[]{author.getAuthorId()});
	}

	public List<Author> readAllAuthors(Integer	pageNo, String searchString) throws SQLException{
		setPageNo(pageNo);
		
		if(searchString !=null && !searchString.isEmpty()){
			searchString = "%"+searchString+"%";
			
			String sql = "select * from tbl_author where authorName like ?";
			
			this.setFetchSize(this.getAuthorsCount(sql, searchString));
			
			if(pageNo != null && pageNo > 0) {
				int limit = (pageNo - 1) * getPageSize();
				sql += " LIMIT " + limit + ", " + getPageSize();
			}
			
			return template.query(sql, new Object[]{searchString},this);
		}else{
			
			String sql = "select * from tbl_author";
			
			this.setFetchSize(this.getAuthorsCount(sql, searchString));
			
			if(pageNo != null && pageNo > 0) {
				int limit = (pageNo - 1) * getPageSize();
				sql += " LIMIT " + limit + ", " + getPageSize();
			}
			
			System.out.println("Query: "+ sql);
			
			return template.query(sql, this);
		}

	}
	public List<Author> readAllAuthors(){
		return template.query("select * from tbl_author", this);
	}

	public Author readByPK(Integer author) throws SQLException {
		List<Author> authors = template.query("select * from tbl_author where authorId = ?", new Object[] {author}, this);
		if(authors!=null){
			return authors.get(0);
		}
		return null;
	}
	public List<Author> readAllAuthorsByBook(Integer book) throws SQLException{
		return template.query("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)",
				new Object[] {book}, this);
	}
	
	public Integer getAuthorsCount(String sql, String searchString) {
		String newSql = "select count(*) " + sql.split("select * ")[1].substring(1);
		
		if(searchString != null && !searchString.isEmpty()) {
			return template.queryForObject(newSql, new Object[]{searchString}, Integer.class);
		} else {
			return template.queryForObject(newSql, Integer.class);
		}
	}
	
	public Integer getAuthorsCount(String searchStr) throws SQLException{
		return readAllAuthors(0, searchStr).size();
	}

	public List<Author> readAllBranch() throws SQLException{
		return template.query("select * from tbl_author", this);
	}

	public Integer getAuthorCount() throws SQLException {
		return template.queryForObject("select count(*) from tbl_author", Integer.class);
	}
	@Override
	public List<Author> extractData(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();
		try {
			while(rs.next()){
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return authors;
	}




}

