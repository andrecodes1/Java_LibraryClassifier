import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class do_part1 {
	boolean clean;
	String CCB = "", HCB = "", MTV = "", MRB = "", NEB = "", OTR = "", SSM = "", TPA = "";
	String errorFile = "";

	public void checkErrorNGenre(String file, String line) throws TooManyFieldsException, FileNotFoundException,
			TooFewFieldsException, MissingFieldException, UnknownGenreException {
		int dq = 0;
		int commaCount = 0;
		String genre = null;
		PrintWriter writer;
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
			errorFile += e.getMessage()+"\n\n";
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

	public void do_part1() throws FileNotFoundException, TooManyFieldsException, TooFewFieldsException,
			MissingFieldException, UnknownGenreException {
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
			System.out.println("syntax_error_file successfully written");
		} catch (IOException e2) {
			System.out.println("An error occurred while writing to the file.");

		}
		try {
			PrintWriter writer = new PrintWriter(new File("Cartoons_Comics.csv.txt"));
			writer.print(CCB);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Hobbies_Collectibles.csv.txt"));
			writer.print(HCB);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Movies_TV_Books.csv.txt"));
			writer.print(MTV);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Music_Radio_Books.csv.txt"));
			writer.print(MRB);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Nostalgia_Eclectic_Books.csv.txt"));
			writer.print(NEB);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Old_Time_Radio_Books.csv.txt"));
			writer.print(OTR);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Sports_Sports_Memorabilia.csv.txt"));
			writer.print(SSM);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Trains_Planes_Automobiles.csv.txt"));
			writer.print(TPA);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}
}
