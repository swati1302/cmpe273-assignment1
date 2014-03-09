package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
    private long isbn;
    private String title;
    private String status;
    @JsonProperty("publication-date")
    private String publicationDate;
    private String language;
    @JsonProperty("num-pages")
    private long numPages;
    @JsonProperty("authors")
    private ArrayList<Author> authors;

    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language 
	 * 			the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status 
	 * 			the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the authors
	 */
	public ArrayList<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	/**
	 * @return the publicationDate
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @param publicationDate the publicationDate to set
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

}
