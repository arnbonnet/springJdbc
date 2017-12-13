package main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import main.repository.BookDAO;


public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		BookDAO bookDAO = context.getBean(BookDAO.class);
		
	//	System.out.println("===== Number of books : " + bookDAO.getBooksCount());
		
		
		// INSERT OPERATION
		//bookDAO.addBook("test2", 570, "mr auteur2", new java.util.Date());
		
		
		// LIST ALL
//		List<Book> books = bookDAO.getAllBooks();
//		System.out.println("======= All books ======");
//		for(Book book : books) {
//			System.out.println(book.toString());
//		}

		// UPDATE BOOK
//		Book b = new Book();
//		b.setTitle("L'assassin royal - Tome 2");
//		b.setNbPages(350);
//		b.setAuthor("Robin Hobb");
//		b.setPublicationDate(new Date());
		
//		bookDAO.updateBook(3L, b);
		
		// DELETE BOOK
//		bookDAO.deleteBook(4L);
		
		// INSERT 3 BOOKS
		//bookDAO.insert3Books();
		
		// INSERT 3 BOOKS WITH SAME ID
		try {
		bookDAO.insert3BooksWithSameId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//bookDAO.insert3Books();
		context.close();
	}

}
