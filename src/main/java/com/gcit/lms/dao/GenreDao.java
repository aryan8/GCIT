/**
 * Genre Aryan
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
import com.gcit.lms.entity.Genre;

/**
 * @Genre Aryan
 *
 */
public class GenreDao extends BaseDAO implements ResultSetExtractor<List<Genre >> {

	@Autowired
	BookDao bdao;
	
	public void addGenre(Genre genre) throws SQLException{
		template.update("insert into tbl_genre (genre_Name) values (?)", new Object[]{genre.getGenreName()});
	}
	public List<Genre> readAllGenresByBook(Integer book) throws SQLException{
		return template.query("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)",
				new Object[] {book}, this);
	}
	public void updateGenre(Genre genre) throws SQLException{
		template.update("update tbl_genre set genre_Name = ? where genre_id = ?", new Object[]{genre.getGenreName(), genre.getGenreId()});
	}
	
	
	public void deleteGenre(Genre genre) throws SQLException{
		template.update("delete from tbl_genre where genre_id = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws SQLException{
		return template.query("select * from tbl_genre", this);
	}
	public Genre readByPK(Integer genre) throws SQLException {
		List<Genre> genres = template.query("select * from tbl_author where authorId = ?", new Object[] {genre},this);
		if(genres!=null && !genres.isEmpty()){
			return genres.get(0);
		}
		return null;
	}
	@Override
	public  List<Genre> extractData(ResultSet rs) {
		List<Genre> genres = new ArrayList<Genre>();
		try {
			while(rs.next()){
				Genre g= new Genre();
				g.setGenreId(rs.getInt("genre_id"));
				g.setGenreName(rs.getString("genre_name"));
				List<Book> books=template.query("select * from tbl_book where bookId IN (Select bookId from tbl_book_genres where genre_id = ?)", new Object[]{g.getGenreId()},bdao);				
				genres.add(g);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}
	

}
