//package main.repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import main.model.Book;
//
//public final class BookMapper implements RowMapper<Book> {
//
//	@Override
//	public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//		Book b = new Book();
//		b.setId(resultSet.getLong("id"));
//		b.setTitle(resultSet.getString("title"));
//		b.setNbPages(resultSet.getInt("nb_pages"));
//		b.setAuthor(resultSet.getString("author"));
//		b.setPublicationDate(resultSet.getDate("publication_date"));
//		return b;
//	}
//	
//	
//}
