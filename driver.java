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
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

/**
 * Driver class, will contain all important methods and the main method.
 * 
 * @author Adam Chami and André Assaad
 *
 */
public class driver {

	static boolean clean;
	static String CCB = "";
	static String HCB = "";
	static String MTV = "";
	static String MRB = "";
	static String NEB = "";
	static String OTR = "";
	static String SSM = "";
	static String TPA = "";
	static String errorFile = "";
	static String semanticErrorFile = "";

	/**
	 * Checks if there are any semantic errors in the descriptions.
	 * 
	 * @param file Which file's descriptions it should check.
	 * @param line Which description in that file it should check.
	 * @throws TooManyFieldsException If too many fields.
	 * @throws FileNotFoundException  If there's a problem with finding the file.
	 * @throws TooFewFieldsException  If too few fields.
	 * @throws MissingFieldException  If there is a field less than there should be.
	 * @throws UnknownGenreException  If there is an invalid genre.
	 */
	public static void checkErrorNGenre(String file, String line) throws TooManyFieldsException, FileNotFoundException,
			TooFewFieldsException, MissingFieldException, UnknownGenreException {
		int dq = 0;
		int commaCount = 0;
		String genre = null;
		try {
			clean = true;
			for (int i = 0; i < line.length(); i++)
				if (line.charAt(i) == '\"')
					dq = i;

			for (int i = dq; i < line.length(); i++)
				if (line.charAt(i) == ',')
					commaCount += 1;

			int comma1 = line.indexOf(',', dq + 1);
			int comma2 = line.indexOf(',', comma1 + 1);
			int comma3 = line.indexOf(',', comma2 + 1);
			int comma4 = line.indexOf(',', comma3 + 1);
			int comma5 = line.indexOf(',', comma4 + 1);

			genre = line.substring(comma4 + 1, comma5);
			if (commaCount > 5) {
				clean = false;
				throw new TooManyFieldsException("syntax error in file: " + file + "\n"
						+ "====================\nError: too many fields\nRecord: " + line);

			} else if (commaCount < 5) {

				clean = false;

				throw new TooFewFieldsException("syntax error in file: " + file + "\n"
						+ "====================\nError: too few fields\nRecord: " + line);

			}

			else if (comma1 == 0) {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing title\nRecord: " + line);
			} else if (line.charAt(comma1 + 1) == line.charAt(comma2)) {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing author\nRecord: " + line);
			} else if (line.charAt(comma2 + 1) == line.charAt(comma3)) {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing price\nRecord: " + line);
			} else if (line.charAt(comma3 + 1) == line.charAt(comma4)) {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing ISBN\nRecord: " + line);
			} else if (line.charAt(comma4 + 1) == line.charAt(comma5)) {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing genre\nRecord: " + line);
			} else if (line.charAt(line.length() - 1) == ',') {

				clean = false;
				throw new MissingFieldException("syntax error in file: " + file + "\n"
						+ "====================\nError: missing year\nRecord: " + line);
			} else if ((!genre.equals("CCB")) && (!genre.equals("HCB")) && (!genre.equals("MTV"))
					&& (!genre.equals("MRB")) && (!genre.equals("NEB")) && (!genre.equals("OTR"))
					&& (!genre.equals("SSM")) && (!genre.equals("TPA"))) {
				clean = false;
				throw new UnknownGenreException("syntax error in file: " + file + "\n"
						+ "====================\nError: invalid genre\nRecord: " + line);
			}

		} catch (TooManyFieldsException | TooFewFieldsException | MissingFieldException | UnknownGenreException e) {
			errorFile += e.getMessage() + "\n\n";
		}
		if (genre.equals("CCB") && clean == true)
			CCB += line + "\n";
		else if (genre.equals("HCB") && clean == true)
			HCB += line + "\n";
		else if (genre.equals("MTV") && clean == true)
			MTV += line + "\n";
		else if (genre.equals("MRB") && clean == true)
			MRB += line + "\n";
		else if (genre.equals("NEB") && clean == true)
			NEB += line + "\n";
		else if (genre.equals("OTR") && clean == true)
			OTR += line + "\n";
		else if (genre.equals("SSM") && clean == true)
			SSM += line + "\n";
		else if (genre.equals("TPA") && clean == true)
			TPA += line + "\n";
	}

	static String[] ar1 = CCB.split("\n");
	static String[] ar2 = HCB.split("\n");
	static String[] ar3 = MTV.split("\n");
	static String[] ar4 = MRB.split("\n");
	static String[] ar5 = NEB.split("\n");
	static String[] ar6 = OTR.split("\n");
	static String[] ar7 = SSM.split("\n");
	static String[] ar8 = TPA.split("\n");

	static Book[] books_CCB = new Book[27];
	static Book[] books_HCB = new Book[37];
	static Book[] books_MTV = new Book[750];
	static Book[] books_MRB = new Book[517];
	static Book[] books_NEB = new Book[54];
	static Book[] books_OTR = new Book[8];
	static Book[] books_SSM = new Book[194];
	static Book[] books_TPA = new Book[37];
	static int a = 0;
	static int b = 0;
	static int c = 0;
	static int d = 0;
	static int e = 0;
	static int f = 0;
	static int g = 0;
	static int h = 0;

	/**
	 * This method will serve to verify if there are any semantic errors in the
	 * descriptions.
	 * 
	 * @param file File to check.
	 * @param line Which line of the file to check.
	 * @throws BadIsbn10Exception    If there's a wrong 10-digit isbn.
	 * @throws FileNotFoundException If file can't be found.
	 * @throws BadIsbn13Exception    If there is a wrong 13-digit isbn.
	 * @throws BadPriceException     If there's an invalid price.
	 * @throws BadYearException      If there's an invalid year.
	 */
	public static void checkSemanticErrors(String file, String line)
			throws BadIsbn10Exception, FileNotFoundException, BadIsbn13Exception, BadPriceException, BadYearException {
		String CCB2 = ""; // genres with 2 are used for binary files in part 2
		String HCB2 = "";
		String MTV2 = "";
		String MRB2 = "";
		String NEB2 = "";
		String OTR2 = "";
		String SSM2 = "";
		String TPA2 = "";
		int dq = 0;
		String title = null;
		String authors = null;
		String isbn = null;
		double price = 0;
		int year = 0;
		String genre = null;
		try {
			clean = true;
			for (int i = 0; i < line.length(); i++)
				if (line.charAt(i) == '\"')
					dq = i;

			int comma1 = line.indexOf(',', dq + 1);
			int comma2 = line.indexOf(',', comma1 + 1);
			int comma3 = line.indexOf(',', comma2 + 1);
			int comma4 = line.indexOf(',', comma3 + 1);
			int comma5 = line.indexOf(',', comma4 + 1);

			title = line.substring(0, comma1);
			authors = line.substring(comma1 + 1, comma2);
			price = Double.parseDouble(line.substring(comma2 + 1, comma3));
			isbn = line.substring(comma3 + 1, comma4);
			genre = line.substring(comma4 + 1, comma5);
			year = Integer.parseInt(line.substring(comma5 + 1));

			if (price < 0) {
				clean = false;
				throw new BadPriceException("semantic error in file: " + file + "\n"
						+ "====================\nError: invalid price\nRecord: " + line);
			}

			else if (isbn.length() == 10) {

				int total = 0;
				for (int i = 0; i < 10; i++) {
					int digit = isbn.charAt(i) - '0';
					total += digit * (10 - i);
				}

				if (total % 11 != 0) {
					clean = false;
					throw new BadIsbn10Exception("semantic error in file: " + file + "\n"
							+ "====================\nError: invalid 10-digit ISBN\nRecord: " + line);
				} else
					clean = true;

			}

			else if (isbn.length() == 13) {

				int total = 0;
				for (int i = 0; i < 13; i++) {
					int digit = isbn.charAt(i) - '0';
					if (i % 2 == 0) {
						total += digit;
					} else {
						total += (3 * digit);
					}
				}
				if (total % 10 != 0) {
					clean = false;
					throw new BadIsbn13Exception("semantic error in file: " + file + "\n"
							+ "====================\nError: invalid 13-digit ISBN\nRecord: " + line);
				}

				else
					clean = true;
			}

			else if (year < 1995 || year > 2010) {

				clean = false;
				throw new BadYearException("semantic error in file: " + file + "\n"
						+ "====================\nError: invalid year\nRecord: " + line);
			}

		} catch (BadPriceException | BadIsbn10Exception | BadIsbn13Exception | BadYearException e) {
			semanticErrorFile += e.getMessage() + "\n\n";
		}

		if (genre.equals("CCB") && clean == true) {
			CCB2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_CCB[a] = book;
			a++;
		} else if (genre.equals("HCB") && clean == true) {
			HCB2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_HCB[b] = book;
			b++;
		} else if (genre.equals("MTV") && clean == true) {
			MTV2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_MTV[c] = book;
			c++;
		} else if (genre.equals("MRB") && clean == true) {
			MRB2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_MRB[d] = book;
			d++;
		} else if (genre.equals("NEB") && clean == true) {
			NEB2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_NEB[e] = book;
			e++;
		} else if (genre.equals("OTR") && clean == true) {
			OTR2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_OTR[f] = book;
			f++;
		} else if (genre.equals("SSM") && clean == true) {
			SSM2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_SSM[g] = book;
			g++;
		} else if (genre.equals("TPA") && clean == true) {
			TPA2 += line + "\n";
			Book book = new Book(title, authors, price, isbn, genre, year);
			books_TPA[h] = book;
			h++;
		}

	}

	static Book[] newBooks_CCB = new Book[21];
	static Book[] newBooks_HCB = new Book[33];
	static Book[] newBooks_MTV = new Book[704];
	static Book[] newBooks_MRB = new Book[468];
	static Book[] newBooks_NEB = new Book[49];
	static Book[] newBooks_OTR = new Book[7];
	static Book[] newBooks_SSM = new Book[180];
	static Book[] newBooks_TPA = new Book[34];

	/**
	 * Will mainly serve to deserialize the objects placed in the binary files in
	 * part 2.
	 * 
	 * @throws ClassNotFoundException Can sometimes not find the class of the
	 *                                object.
	 */
	public static void deserialize() throws ClassNotFoundException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Cartoons_Comics.csv.ser"));
			newBooks_CCB = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Cartoons_Comics.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Hobbies_Collectibles.csv.ser"));
			newBooks_HCB = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Hobbies_Collectibles. \n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Movies_TV_Books.csv.ser"));
			newBooks_MTV = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Movies_TV_Books.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Music_Radio_Books.csv.ser"));
			newBooks_MRB = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Music_Radio_Books.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Nostalgia_Eclectic_Books.csv.ser"));
			newBooks_NEB = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Nostalgia_Eclectic_Books.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Old_Time_Radio_Books.csv.ser"));
			newBooks_OTR = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Old_Time_Radio_Books.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Sports_Sports_Memorabilia.csv.ser"));
			newBooks_SSM = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Sports_Sports_Memorabilia.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Trains_Planes_Automobiles.csv.ser"));
			newBooks_TPA = (Book[]) ois.readObject();
			ois.close();
			System.out.println(
					"Successfully created a deserialized array of book objects with genre Trains_Planes_Automobiles.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the contents of the file.");
			e.printStackTrace();
		}

	}

	/**
	 * Part 1 of the assignment. Executes the syntax error checker for each line in
	 * each file, then writes a file containing all the descriptions having syntax
	 * errors. Also creates files for each genre, with each book with that genre
	 * having its description in the file.
	 * 
	 * @throws FileNotFoundException  Can't find file.
	 * @throws TooManyFieldsException Too many fields.
	 * @throws TooFewFieldsException  Not enough fields.
	 * @throws MissingFieldException  Missing field.
	 * @throws UnknownGenreException  Invalid genre.
	 */
	public static void do_part1() throws FileNotFoundException, TooManyFieldsException, TooFewFieldsException,
			MissingFieldException, UnknownGenreException {
		System.out.println(
				"==========================================================\n  Hello and welcome to the Java Library Book Classifier\n==========================================================");
		System.out.println("\nClassification will start in: ");
		for (int i = 3; i >= 1; i--) {
			System.out.print(i + "...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(
				"\n\nClassification Initialized: \n\n--------------------------------------------------------------");

		try {
			File fileList = new File("part1_input_file_names.txt");
			Scanner sc1 = new Scanner(fileList);
			if (sc1.hasNextLine())
				sc1.nextLine();

			while (sc1.hasNextLine()) {
				String listLine = sc1.nextLine();
				File bookList = new File(listLine);
				Scanner sc2 = new Scanner(bookList);
				while (sc2.hasNextLine()) {
					String bookLine = sc2.nextLine();
					checkErrorNGenre(listLine, bookLine);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			PrintWriter writer = new PrintWriter(new File("syntax_error_file.txt"));
			writer.println(errorFile);
			writer.close();
			System.out.println(
					"syntax_error_file.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e2) {
			System.out.println("An error occurred while writing to the file.");

		}
		try {
			PrintWriter writer = new PrintWriter(new File("Cartoons_Comics.csv.txt"));
			writer.print(CCB);
			writer.close();
			System.out.println(
					"Cartoons_Comics.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Hobbies_Collectibles.csv.txt"));
			writer.print(HCB);
			writer.close();
			System.out.println(
					"Hobbies_Collectibles.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Movies_TV_Books.csv.txt"));
			writer.print(MTV);
			writer.close();
			System.out.println(
					"Movies_TV_Books.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Music_Radio_Books.csv.txt"));
			writer.print(MRB);
			writer.close();
			System.out.println(
					"Music_Radio_Books.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Nostalgia_Eclectic_Books.csv.txt"));
			writer.print(NEB);
			writer.close();
			System.out.println(
					"Nostalgia_Eclectic_Books.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Old_Time_Radio_Books.csv.txt"));
			writer.print(OTR);
			writer.close();
			System.out.println(
					"Old_Time_Radio_Books.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Sports_Sports_Memorabilia.csv.txt"));
			writer.print(SSM);
			writer.close();
			System.out.println(
					"Sports_Sports_Memorabilia.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Trains_Planes_Automobiles.csv.txt"));
			writer.print(TPA);
			writer.close();
			System.out.println(
					"Trains_Planes_Automobiles.csv.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}

	/**
	 * This part is where we write to the binary files. For each genre, there is now
	 * a binary file with an array of books from that genre.
	 * 
	 * @throws FileNotFoundException Can't find file.
	 * @throws BadIsbn10Exception    Invalid 10-digit isbn.
	 * @throws BadIsbn13Exception    Invalid 13-digit isbn.
	 * @throws BadPriceException     Invalid price.
	 * @throws BadYearException      Invalid year.
	 */
	public static void do_part2()
			throws FileNotFoundException, BadIsbn10Exception, BadIsbn13Exception, BadPriceException, BadYearException {
		System.out.println(
				"==========================================================\n  Now starting part 2\n==========================================================");
		System.out.println("\nClassification will start in: ");
		for (int i = 3; i >= 1; i--) {
			System.out.print(i + "...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(
				"\n\nClassification Initialized: \n\n--------------------------------------------------------------");

		try {
			File fileList2 = new File("part2_input_file_names.txt");
			Scanner sc1 = new Scanner(fileList2);
			if (sc1.hasNextLine())
				sc1.nextLine();

			while (sc1.hasNextLine()) {
				String listLine2 = sc1.nextLine();
				File bookList2 = new File(listLine2);
				Scanner sc2 = new Scanner(bookList2);
				while (sc2.hasNextLine()) {
					String bookLine = sc2.nextLine();
					checkSemanticErrors(listLine2, bookLine);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			PrintWriter writer = new PrintWriter(new File("semantic_error_file.txt"));
			writer.println(semanticErrorFile);
			writer.close();
			System.out.println(
					"semantic_error_file.txt file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e2) {
			System.out.println("An error occurred while writing to the file.");

		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Cartoons_Comics.csv.ser"));
			oos.writeObject(books_CCB);
			oos.close();
			System.out.println(
					"Cartoons_Comics.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Hobbies_Collectibles.csv.ser"));
			oos.writeObject(books_HCB);
			oos.close();
			System.out.println(
					"Hobbies_Collectibles.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Movies_TV_Books.csv.ser"));
			oos.writeObject(books_MTV);
			oos.close();
			System.out.println(
					"Movies_TV_Books.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Music_Radio_Books.csv.ser"));
			oos.writeObject(books_MRB);
			oos.close();
			System.out.println(
					"Music_Radio_Books.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Nostalgia_Eclectic_Books.csv.ser"));
			oos.writeObject(books_NEB);
			oos.close();
			System.out.println(
					"Nostalgia_Eclectic_Books.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Old_Time_Radio_Books.csv.ser"));
			oos.writeObject(books_OTR);
			oos.close();
			System.out.println(
					"Old_Time_Radio_Books.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Sports_Sports_Memorabilia.csv.ser"));
			oos.writeObject(books_SSM);
			oos.close();
			System.out.println(
					"Sports_Sports_Memorabilia.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Trains_Planes_Automobiles.csv.ser"));
			oos.writeObject(books_TPA);
			oos.close();
			System.out.println(
					"Trains_Planes_Automobiles.csv.ser file written successfully.\n--------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}

	/**
	 * This method will keep asking the user if they want to view some records from
	 * each genre. This will use the new array of deserialized objects.
	 * 
	 */

public static int current=0;
/**
 * This method is used to show the current book in the viewing system. 
 * @param books books
 */
	private static void displayCurrentBook(Book[] books) {
		System.out.println("Current book: " + books[current]);
	}
/**
 * This method is the viewing system. 
 * @param books books
 * @param startIndex startIndex
 * @param range range
 */
	public static void displayBooks(Book[] books, int startIndex, int range) {
	    int endIndex = startIndex + range - 1;
	    if (startIndex < 0) {
	        System.out.println("BOF has been reached");
	        startIndex = 0;
	    }
	    if (endIndex >= books.length) {
	        endIndex = books.length - 1;
	    }
	    for (int i = startIndex; i <= endIndex; i++) {
	        System.out.println(books[i]);
	    }
	   
	    	current = endIndex;


	    if (endIndex >= books.length - 1) {
	        System.out.println("EOF has been reached");
	    }
	}
/**
 * This method is part3. It reads the binary file, deserialize the array objects in each file. Finally it provides 
 * a program that the user can interact with to view the desired records. 
 * @throws ClassNotFoundException Class Can't Be Found
 */
	public static void do_part3() throws ClassNotFoundException {
		deserialize();
		Scanner kb = new Scanner(System.in);

		String strCCB = "Cartoons_Comics_Books.csv.ser              (21 records)";
		String strHCB = "Hobbies_Collectibles_Books.csv.ser         (33 records)";
		String strMTV = "Movies_TV_Books.csv.ser                    (704 records)";
		String strMRB = "Music_Radio_Books.csv.ser                  (468 records)";
		String strNEB = "Nostalgia_Eclectic_Books.csv.ser           (49 records)";
		String strOTR = "Old_Time_Radio.csv.ser                     (7 records)";
		String strSSM = "Sports_Sports_Memorabilia.csv.ser          (180 records)";
		String strTPA = "Trains_Planes_Automobiles.csv.ser          (34 records)";
		int genre_number = 0;
		String str = "";

		while (genre_number != 9) {

			System.out.println("-------------------------------");
			System.out.println("         File Sub-Menu         ");
			System.out.println("-------------------------------");
			System.out.println(" 1  " + strCCB);
			System.out.println(" 2  " + strHCB);
			System.out.println(" 3  " + strMTV);
			System.out.println(" 4  " + strMRB);
			System.out.println(" 5  " + strNEB);
			System.out.println(" 6  " + strOTR);
			System.out.println(" 7  " + strSSM);
			System.out.println(" 8  " + strTPA);
			System.out.println(" 9  Exit");
			System.out.println("-------------------------------");
			System.out.print("\nEnter Your Choice: ");
			genre_number = kb.nextInt();

			if (genre_number == 9) {
				System.out.println("Program will terminate");
				System.exit(0);
			} else if (genre_number == 1)
				str = strCCB;
			else if (genre_number == 2)
				str = strHCB;
			else if (genre_number == 3)
				str = strMTV;
			else if (genre_number == 4)
				str = strMRB;
			else if (genre_number == 5)
				str = strNEB;
			else if (genre_number == 6)
				str = strOTR;
			else if (genre_number == 7)
				str = strSSM;
			else if (genre_number == 8)
				str = strTPA;
			else {
				System.out.println("Please enter a valid number.");
				continue;
			}

			String option = "o";
			while (!option.equals("u")) {
				System.out.println("-------------------------------");
				System.out.println("           Main Menu           ");
				System.out.println("-------------------------------");
				System.out.println(" v  View the selected file: " + str);
				System.out.println(" s  Select a file to view");
				System.out.println(" x  Exit");
				System.out.println("-------------------------------");
				System.out.print("\nEnter Your Choice: ");
				option = kb.next().substring(0, 1).toLowerCase();

				if (option.equals("x")) {
					System.out.println("Program will terminate");
					System.exit(0);
				}

				else if (option.equals("s"))
					option = "u";

				else if (option.equals("v")) {
					int n;
					int i;
					boolean whileLoop = true;
					while (whileLoop) {
						System.out.println("\nViewing: " + str);
						System.out.println(
								"Please pick a number. Here are some results based on the number you pick:\n ");
						System.out.println("\t0:  End the viewing session");
						System.out.println("\t+1 or - 1: Display current record");
						System.out.println("\tAny other number will show said record and all subsequent records");

						System.out.print("\nChoose your number: ");
						n = kb.nextInt();
						System.out.println();

						if (n == 0)
							whileLoop = false;

						if (n == -1 || n == 1) {
							if (str.equals(strCCB)) {
								displayCurrentBook(newBooks_CCB);
							} else if (str.equals(strHCB)) {
								displayCurrentBook(newBooks_HCB);
							} else if (str.equals(strMTV)) {
								displayCurrentBook(newBooks_MTV);
							} else if (str.equals(strMRB)) {
								displayCurrentBook(newBooks_MRB);
							} else if (str.equals(strNEB)) {
								displayCurrentBook(newBooks_NEB);
							} else if (str.equals(strOTR)) {
								displayCurrentBook(newBooks_OTR);
							} else if (str.equals(strSSM)) {
								displayCurrentBook(newBooks_SSM);
							} else if (str.equals(strTPA)) {
								displayCurrentBook(newBooks_TPA);
							}

						}
				        else if (n > 0) {
				        	if (str.equals(strCCB)) {
								displayBooks(newBooks_CCB,current, n);
							} else if (str.equals(strHCB)) {
								displayBooks(newBooks_HCB,current, n);
							} else if (str.equals(strMTV)) {
								displayBooks(newBooks_MTV, current,n);
							} else if (str.equals(strMRB)) {
								displayBooks(newBooks_MRB,current,n);
							} else if (str.equals(strNEB)) {
								displayBooks(newBooks_NEB,current,n);
							} else if (str.equals(strOTR)) {
								displayBooks(newBooks_OTR,current,n);
							} else if (str.equals(strSSM)) {
								displayBooks(newBooks_SSM,current,n);
							} else if (str.equals(strTPA)) {
								displayBooks(newBooks_TPA,current,n);
							}
			            } else {
				        	if (str.equals(strCCB)) {
								displayBooks(newBooks_CCB,current + n + 1, -n);
							} else if (str.equals(strHCB)) {
								displayBooks(newBooks_HCB,current + n + 1, -n);
							} else if (str.equals(strMTV)) {
								displayBooks(newBooks_MTV, current + n + 1, -n);
							} else if (str.equals(strMRB)) {
								displayBooks(newBooks_MRB,current + n + 1, -n);
							} else if (str.equals(strNEB)) {
								displayBooks(newBooks_NEB,current + n + 1, -n);
							} else if (str.equals(strOTR)) {
								displayBooks(newBooks_OTR,current + n + 1, -n);
							} else if (str.equals(strSSM)) {
								displayBooks(newBooks_SSM,current + n + 1, -n);
							} else if (str.equals(strTPA)) {
								displayBooks(newBooks_TPA,current + n + 1, -n);
							}
			            }
						
						
					}
				}

			}
		}
		kb.close();
	}

	/**
	 * Main method
	 * 
	 * @param args args
	 * @throws FileNotFoundException  Can't find file.
	 * @throws TooManyFieldsException Too many fields.
	 * @throws TooFewFieldsException  Too few fields.
	 * @throws MissingFieldException  Missing field.
	 * @throws UnknownGenreException  Invalid genre.
	 * @throws BadIsbn10Exception     Bad 10-digit isbn.
	 * @throws BadIsbn13Exception     Bad 13-digit isbn.
	 * @throws BadPriceException      Bad price.
	 * @throws BadYearException       Invalid year.
	 * @throws ClassNotFoundException Can't find class of object.
	 */
	public static void main(String[] args) throws FileNotFoundException, TooManyFieldsException, TooFewFieldsException,
			MissingFieldException, UnknownGenreException, BadIsbn10Exception, BadIsbn13Exception, BadPriceException,
			BadYearException, ClassNotFoundException {
		do_part1();
		System.out.println("\n");
		do_part2();
		System.out.println("\n");
		do_part3();

	}

}
