package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;

public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);

	ArrayList<Author> authors = newBook.getAuthors();
	int i = 0;
	
	for (Author item: authors) {
		item.setId(++i);
		//item.setName(newBook.get);
		System.out.println("name:"+item.getName());
	}

	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    @Override
    public Book removeBookByISBN(Long isbn) {
    checkArgument(isbn > 0, "ISBN was %s but expected greater than zero value", isbn);
    Book previousValue = bookInMemoryMap.remove(isbn);
    	return previousValue;
    }
    
    public void updateBookInfo(Book book, Entry<String, List<String>> entry) {
    	String str = entry.getValue().toString();
    	if (entry.getKey().equals("status")) {
    		book.setStatus(str.substring(1, str.length()));
    	}
    	else if (entry.getKey().equals("title")) {
    		book.setTitle(str.substring(1, str.length()));
    	}
    	else if(entry.getKey().equals("language")) {
    		book.setLanguage(str.substring(1, str.length()));
    	}
    	else if(entry.getKey().equals("publication-date")) {
    		book.setPublicationDate(str.substring(1, str.length()));
    	}
    }
}
