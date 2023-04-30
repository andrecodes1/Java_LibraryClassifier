 // -----------------------------------------------------
 // Assignment 3
 // Written by: André Assaad (40242006) and Adam Chami (40248165)
 // -----------------------------------------------------

/**
 * André Assaad (40242006) and Adam Chami (40248165)
 * COMP249
 * Assignment 3
 * Due Date: Wednesday 29th March
 */

import java.io.Serializable;

/**
 * The book class, which will be used to create book objects in part 2. Note that this class implements serializable
 * @author Adam Chami and André Assaad
 *
 */
public class Book implements Serializable{

	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	/**
	 * The constructor for a book object. Each parameter is a field of the book's description.
	 * @param title title
	 * @param authors author names
	 * @param price book price
	 * @param isbn book's isbn
	 * @param genre book,s genre
	 * @param year release year
	 */
	public Book(String title, String authors, double price, String isbn, String genre, int year) {
		this.title = title;
		this.authors = authors;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}

	/**
	 * getter for title.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setter for title.
	 * @param title desired title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * getter for authors.
	 * @return author
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * setter for authors.
	 * @param authors desired author name(s)
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * getter for price.
	 * @return price price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * setter for price.
	 * @param price desired price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * getter for isbn.
	 * @return isbn isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * setter for isbn.
	 * @param isbn desired isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * getter for genre.
	 * @return genre genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * setter for genre.
	 * @param genre desired genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * getter for year.
	 * @return year year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * setter for year.
	 * @param year desired year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * toString method for a book class. Will be used to describe each book object.
	 */
	public String toString() {
		return "Title: " + title + ", Authors: " + authors + ", Price: " + price + ", ISBN: " + isbn + ", Genre: "
				+ genre + ", Year: " + year;
	}

	/**
	 * Equals method for the book class. Verifies if one book object is the same as another book object.
	 * @param book book to be compared.
	 * @return true if they are the same, false if not.
	 */
	public boolean equals(Book book) {
		if (this == null || book==null || getClass() != book.getClass())
			return false;
		else if (title.equals(book.getTitle()) && authors.equals(book.getAuthors()) && price == book.getPrice()
				&& isbn.equals(book.getIsbn()) && genre.equals(book.getGenre()) && year == book.getYear())
			return true;
		else 
			return false;
	}
	
	
	
	
	
}
