/**
 * Author Aryan
 */
/**
 * Author Aryan
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class BookDao extends BaseDAO implements ResultSetExtractor<List<Book>>{
	@Autowired
	AuthorDao adao;
	@Autowired
	GenreDao gdao;

	public List<Book> readBranchBookByCopies(Integer branchId, Integer cardNo) throws SQLException{
		return template.query("SELECT * " +"FROM tbl_book " + "INNER JOIN tbl_book_copies " +"ON tbl_book_copies.bookId = tbl_book.bookId " +"INNER JOIN tbl_library_branch " +"ON tbl_library_branch.branchId = tbl_book_copies.branchId " + "WHERE tbl_book_copies.noOfCopies > 0 AND tbl_library_branch.branchId = ? AND tbl_book.bookId NOT IN ( " +"SELECT bl.bookId "+"FROM tbl_book_loans bl " +"WHERE bl.cardNo = ?)", new Object[]{branchId,cardNo },this);
		//it was reasFirstLevel
	}
	public List<Book> showBorrowedBook(Integer branchId, Integer cardNo) throws SQLException{
		return template.query("select * from `tbl_book` join `tbl_book_loans` on `tbl_book_loans`.`bookId`=`tbl_book`.`bookId` where `tbl_book_loans`.`branchId` = ? and `tbl_book_loans`.`cardNo` =?", new Object[]{branchId,cardNo },this);
	}


	public void addBookAuthors(Integer book, Integer author) throws SQLException{
		template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[]{book, author});
	}
	public void addBookGenres(Integer book, Integer genres) throws SQLException{
		template.update("insert into tbl_book_genres (bookId, genre_id) values (?,?)", new Object[]{book, genres});
	}
	public Integer addBookWithID(Book book) throws SQLException{

		final String title = book.getTitle();
		final String INSERT_SQL = "insert into tbl_book (title) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(INSERT_SQL, new String[] { "bookId" });
				ps.setString(1, title);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}


	public void addBook(final Book book) throws Exception {
		System.out.println(book.getTitle() + " 00 " +  book.getPublisher().getPublisherId());
		KeyHolder keyHolder = new GeneratedKeyHolder();


		final String insertQuery = "insert into tbl_book (title, pubId) values(?,?)";
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement( insertQuery, new String[] { "bookId" });
				ps.setObject(1, book.getTitle());
				ps.setObject(2, book.getPublisher().getPublisherId());
				return ps;
			}
		}, keyHolder);

		Integer bookId = keyHolder.getKey().intValue();

		for(Author a: book.getAuthors()){ 
			template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)", 
					new Object[]{bookId, a.getAuthorId()});
		}

		for(Genre g: book.getGenres()){
			template.update("insert into tbl_book_genres (bookId, genre_id) values (?,?)", 
					new Object[]{bookId, g.getGenreId()});
		}

	}

	public void updateBook(Book book) throws SQLException{
		template.update("update tbl_book set title = ? where bookId = ?", new Object[]{book.getTitle(), book.getBookId()});
	}

	public void updateBook(Integer bookId, String title, Integer[] authorIDs, Integer[] genreIDs, Integer publisherId) throws SQLException{

		template.update("delete from tbl_book_authors where bookId=?", new Object[]{bookId});
		template.update("delete from tbl_book_genres where bookId=?", new Object[]{bookId});

		ArrayList<Object> params = new ArrayList<Object>();
		String query = "insert into tbl_book_authors values ";
		for(Integer authorId: authorIDs){
			params.add(bookId);
			params.add(authorId);
			query += "(?,?), ";
		}
		query = query.substring(0, query.length()-2);
		template.update(query, params.toArray());

		params = new ArrayList<Object>();
		query = "insert into tbl_book_genres values ";
		for(Integer genreId: genreIDs){
			params.add(genreId);
			params.add(bookId);
			query += "(?,?), ";
		}
		query = query.substring(0, query.length()-2);
		template.update(query, params.toArray());

		template.update("update tbl_book set title = ?, pubId=? where bookId = ?", new Object[]{title, publisherId, bookId});
	}


	public void deleteBook(Book book) throws SQLException{
		template.update("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}

	public List<Book> readAllBooks(String searchString) throws SQLException{
		if(searchString !=null && !searchString.isEmpty()){
			searchString = "%"+searchString+"%";
			String sql = "select * from tbl_book where title like ?";
			return template.query(sql, new Object[]{searchString},this);
		}else{
			String sql = "select * from tbl_book";

			return template.query(sql, this);
		}
	}
	public List<Book> readAllBooks() throws SQLException{
		return template.query("select * from tbl_book inner join tbl_publisher on `tbl_book`.`pubId` = `tbl_publisher`.`publisherId`", this);

	}
	public Book readByPK(Book book) throws SQLException{
		List<Book> books = template.query("select * from tbl_book where bookId = ?", new Object[]{book.getBookId()},this);
		if(books != null){
			return books.get(0);
		}
		return null;

	}





	@Override
	public List<Book> extractData(ResultSet rs) {
		List<Book> books = new ArrayList<Book>();
		Publisher pub = new Publisher();


		try {
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				pub.setPublisherId(rs.getInt("pubId"));
				b.setPublisher(pub);
				//Author Authors= (Author) template.query("select * from tbl_author where authorId IN (Select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()},adao);
				//	Genre genres= (Genre) template.query("select * from tbl_genre where genre_Id IN(Select genre_Id from tbl_book_genres where bookId = ?)", new Object[]{b.getBookId()},gdao);				
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return books;
	}

}

