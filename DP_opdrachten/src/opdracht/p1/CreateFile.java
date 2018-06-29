package opdracht.p1;

import java.io.*;
import java.util.Formatter;

public class CreateFile {
	private Formatter x;
	
	public void newFile() {
		try {
			x = new Formatter("p1.csv");
		}
		catch(Exception e) {
			System.out.println("niet gelukt");
		}
	}
	
	public void addRecord() {
		x.format("%s%s%s", "Doeke", "Roos", "20");
		x.format("%s%s%s", "Victor", "Silvis", "19");
	}
	
	public void removeRecord() {
		try {
		File inputFile = new File("p1.csv");
		File tempFile = new File("TempFile.csv");
		System.out.println("oke");
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = "Victor";
		String currentLine;
		System.out.println("oke");
		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
			System.out.println("oke2");
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		System.out.println("nog steeds");
		writer.close(); 
		reader.close(); 
		boolean successful = tempFile.renameTo(inputFile);
	} catch(Exception e) {
			System.out.println("niet gelukt");
		}
	}
	
	public void closeFile() {
		x.close();
		
	}
}
