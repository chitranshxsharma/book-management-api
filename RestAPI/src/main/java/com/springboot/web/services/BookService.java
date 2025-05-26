package com.springboot.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.web.dao.BookRepository;
import com.springboot.web.entities.Book;

@Component
public class BookService {

	
	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Autowired
	private BookRepository bookRepository;
//	private static List<Book> list = new ArrayList<>();
//	
//	static {
//		list.add(new Book(12, "Java Reference", "XYZ"));
//		list.add(new Book(26, "Head first to Java", "ABC"));
//		list.add(new Book(12234, "Thing in Java ", "LNM"));
//
//	}
	
	public List<Book> getAllBooks()
	{
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}
	
	public   Book getBookById(int id) {
		Book book = null;
		try {
			
		
		//book = list.stream().filter(e -> e.getId()==id).findFirst().get();
			book = this.bookRepository.findById(id); 	 	
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return book;
	}
	
	public Book addBook(Book b) {
		//list.add(b);
		Book result = bookRepository.save(b);
		return b;
	}
	
	public void deleteBook(int bid) {
			/*list  = list.stream().filter(book->{
				if(book.getId()!=bid)
				{
					return true;
				}
				else {
					return false;
				}
			}).collect(Collectors.toList());*/
			
			bookRepository.deleteById(bid);
	}
	
	
	public void UpdateBook(Book book, int bookId) {
		/*
		 * list = list.stream().map(b ->{ if(b.getId() == bookId) {
		 * b.setTitle(book.getTitle()); b.setAuthor(book.getAuthor()); } return b;
		 * }).collect(Collectors.toList());
		 */
		book.setId(bookId);
		bookRepository.save(book);
	}
}

