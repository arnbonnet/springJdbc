package main.repository;

import java.util.Date;

import main.model.Book;

public interface IBookDAO {
	
	public void addBook(String title, int nbPages, String author, Date date);
	
//	public List<Book> getAllBooks() ;
	
	public void updateBook(Long id, Book book);
	
	public void deleteBook(Long id) ;
	
	public void insert3Books() ;

	public void insert3BooksWithSameId() ;
}
