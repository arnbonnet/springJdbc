package main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import main.model.Book;

public class BookRowCallbackHandler implements RowCallbackHandler {

	private List<Book> books;
	private List<String> authors;

	public BookRowCallbackHandler() {
		books = new ArrayList<Book>();
		authors = new ArrayList<String>();
	}

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int rowNum = 1;
		do {
			Book book = RowToBook.mapRow(rs, rowNum);
			books.add(book);
			if(!authors.contains(book.getAuthor())) {
				authors.add(book.getAuthor());
			}
			rowNum++;
		} while (rs.next());
	}

	public List<Book> getBooks() {
		return books;
	}
	public List<String> getAuthors() {
		return authors;
	}
	

}
