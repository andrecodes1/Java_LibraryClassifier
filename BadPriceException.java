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

/**
 * This is the exception class for an invalid price
 * @author Adam Chami and André Assaad
 *
 */
public class BadPriceException extends Exception{
	/**
	 * Constructor for exception
	 * @param message thrown
	 */
	public BadPriceException(String p) {
		super(p);
	}
}