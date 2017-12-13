package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import main.model.Book;
import main.repository.BookDAO;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);

		BookDAO bookDAO = context.getBean(BookDAO.class);

		// System.out.println("===== Number of books : " + bookDAO.getBooksCount());

		// INSERT OPERATION
		//bookDAO.addBook(20L, "test2", 570, "mr auteur2", new java.util.Date());

		// LIST ALL
		// List<Book> books = bookDAO.getAllBooks();
		// System.out.println("======= All books ======");
		// for(Book book : books) {
		// System.out.println(book.toString());
		// }

		// UPDATE BOOK
		// Book b = new Book();
		// b.setTitle("L'assassin royal - Tome 2");
		// b.setNbPages(350);
		// b.setAuthor("Robin Hobb");
		// b.setPublicationDate(new Date());

		// bookDAO.updateBook(3L, b);

		// DELETE BOOK
		// bookDAO.deleteBook(4L);

		// INSERT 3 BOOKS
		//bookDAO.insert3Books();

		// INSERT 3 BOOKS WITH SAME ID
		Book b1 = new Book();
		b1.setId(9L);
		b1.setTitle("L'assassin royal - Tome 6");
		b1.setNbPages(350);
		b1.setAuthor("Robin Hobb");
		b1.setPublicationDate(new Date());

		Book b2 = new Book();
		b2.setId(9L);
		b2.setTitle("L'assassin royal - Tome 7");
		b2.setNbPages(350);
		b2.setAuthor("Robin Hobb");
		b2.setPublicationDate(new Date());

		Book b3 = new Book();
		b3.setId(9L);
		b3.setTitle("L'assassin royal - Tome 8");
		b3.setNbPages(350);
		b3.setAuthor("Robin Hobb");
		b3.setPublicationDate(new Date());

		// bookDAO.insert3BooksWithSameId(b1,b2,b3);

		// LIST BOOKS WITH CALLBACK HANDLER
		List<Book> booksHandler = bookDAO.getAllBooksWithCallBackHandler();
		for (Book book : booksHandler) {
			System.out.println(book.toString());
		}

		// LIST AUTHORS WITH CALLBAKC HANDLER
		List<String> authorsHandler = bookDAO.getAllAuthorsWithCallBackHandler();
		System.out.print("Authors : ");
		String comma = "";
		for (String author : authorsHandler) {
			System.out.print(comma);
			System.out.print(author);
			comma = ", ";
			
		}
		System.out.println();
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("books.csv");
			fileWriter.append(bookDAO.getBooksCSV());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

		context.close();
	}

}
