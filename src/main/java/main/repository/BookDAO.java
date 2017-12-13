package main.repository;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.model.Book;

@Repository
@Transactional
public class BookDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public int getBooksCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
	}
	
	public void addBook(Long id, String title, int nbPages, String author, Date date) {
		String sql = "insert into book(id, title, nb_pages, author, publication_date) values(?,?,?,?,?)";
		this.jdbcTemplate.update(sql, id, title, nbPages, author, date);
	}
	
	public List<Book> getAllBooks() {
		return this.jdbcTemplate.query("select * from book", new BookMapper());
	}
	
	public List<Book> getAllBooksWithCallBackHandler() {
		BookRowCallbackHandler bookRowCallbackHandler = new BookRowCallbackHandler();
		this.jdbcTemplate.query("select * from book", bookRowCallbackHandler);
		return bookRowCallbackHandler.getBooks();
	}
	
	public List<String> getAllAuthorsWithCallBackHandler() {
		BookRowCallbackHandler bookRowCallbackHandler = new BookRowCallbackHandler();
		this.jdbcTemplate.query("select * from book", bookRowCallbackHandler);
		return bookRowCallbackHandler.getAuthors();
	}
	
	public void updateBook(Long id, Book book) {
		this.jdbcTemplate.update("update book set title = ?, nb_pages = ?, author = ?, publication_date = ? where id = ?", 
								book.getTitle(), book.getNbPages(), book.getAuthor(), book.getPublicationDate(), id);
	}
	
	public void deleteBook(Long id) {
		this.jdbcTemplate.update("delete from book where id = ?", id);
	}
	
	public void insert3Books() {
		String sql = "insert into book(id, title, nb_pages, author, publication_date) values(?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, 1, "L'assassin royal - Tome 3", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 2, "L'assassin royal - Tome 4", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 15, "L'assassin royal - Tome 5", 350, "Robin Hobb", new Date());
	}

	public void insert3BooksWithSameId(Book b1, Book b2, Book b3) {
		String sql = "insert into book values(?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, b1.getId(), b1.getTitle(), b1.getNbPages(), b1.getAuthor(), new Date());
		this.jdbcTemplate.update(sql, b1.getId(), b2.getTitle(), b2.getNbPages(), b2.getAuthor(), new Date());
		this.jdbcTemplate.update(sql, b1.getId(), b3.getTitle(), b3.getNbPages(), b3.getAuthor(), new Date());
	}
	
	public String getBooksCSV() {
		return this.jdbcTemplate.query("select * from book", new BookListResulstSet());
	}
}
