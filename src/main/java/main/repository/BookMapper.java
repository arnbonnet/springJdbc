package main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import main.model.Book;

public final class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return RowToBook.mapRow(resultSet, rowNum);
	}
	
}
