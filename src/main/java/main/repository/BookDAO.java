package main.repository;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import main.model.Book;

@Repository
@EnableTransactionManagement
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
	
	public void addBook(String title, int nbPages, String author, Date date) {
		String sql = "insert into book(title, nb_pages, author, publication_date) values(?,?,?,?)";
		this.jdbcTemplate.update(sql, title, nbPages, author, date);
	}
	
/*	public List<Book> getAllBooks() {
		return this.jdbcTemplate.query("select * from book", new BookMapper());
	}*/
	
	public void updateBook(Long id, Book book) {
		this.jdbcTemplate.update("update book set title = ?, nb_pages = ?, author = ?, publication_date = ? where id = ?", 
								book.getTitle(), book.getNbPages(), book.getAuthor(), book.getPublicationDate(), id);
	}
	
	public void deleteBook(Long id) {
		this.jdbcTemplate.update("delete from book where id = ?", id);
	}
	
	public void insert3Books() {
		String sql = "insert into book(id,title, nb_pages, author, publication_date) values(?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, 1, "L'assassin royal - Tome 3", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 2, "L'assassin royal - Tome 4", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 3, "L'assassin royal - Tome 5", 350, "Robin Hobb", new Date());
	}

	public void insert3BooksWithSameId() throws Exception {
		String sql = "insert into book(id, title, nb_pages, author, publication_date) values(?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, 9, "L'assassin royal - Tome 6", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 9, "L'assassin royal - Tome 7", 350, "Robin Hobb", new Date());
		this.jdbcTemplate.update(sql, 9, "L'assassin royal - Tome 8", 350, "Robin Hobb", new Date());
	}
}
