package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CSVLibrairie {

	/**
	 * 
	 * @param csvFile chemin vers le fichier
	 * @param csvSplitChar split qui sépare les donnée
	 * @return An arraylist of String where each String is a full line of the
	 *         file without comma separators.
	 */
	public static ArrayList<String[]> readCSV(File csvFile, String csvSplitChar) {
		BufferedReader br = null; // Simple and versatile reader for input
									// character streams.
		String line = "";
		String[] lineBuilder = null;
		ArrayList<String[]> result = new ArrayList<String[]>();
		;

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile.toString()), "UTF-8"));
			while ((line = br.readLine()) != null) {

				lineBuilder = line.split(csvSplitChar);

				/*
				 * for(int i = 0; i < lineBuilder.length;i++){
				 * System.out.println(lineBuilder[i]); }
				 */

				result.add(lineBuilder);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close(); // Will it close?
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static ArrayList<String[]> readCSV(File csv) {
		return readCSV(csv, ",");
	}

	/**
	 * 
	 * Save an ArrayList of array of values in a csv file.
	 * 
	 * @param file
	 * 
	 *            Path of the file to read
	 * 
	 * @param list
	 * 
	 *            List of the values to write in the file.
	 * 
	 * @param csvSplitChar
	 * 
	 *            Character used to delimit values
	 *            
	 * @return boolean true, si tout c'est bien passé, false sinon
	 */

	public static boolean saveCSV(File file, ArrayList<String[]> list, String csvSplitChar) {

		try {
			file.createNewFile();
				
		}

		catch (IOException e) {
			System.out.println("Path incorrect");
			return false;
		}

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(file);

			/* TODO: delete file content if it's not empty */

			for (String[] lineEntry : list) {

				for (int i = 0; i < lineEntry.length; i++) {
					fileWriter.append(lineEntry[i]);
					
					if(i!=lineEntry.length-1)//Don't put a comma at the end of the line
						fileWriter.append(csvSplitChar);
				}

				fileWriter.append("\n");
			}

		}

		catch (Exception e) {
			System.out.println("Error in FileWriter !!!");
			return false;
		}

		finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}

			catch (IOException e) {

				System.out.println("Error while flushing/closing fileWriter !!!");
				return false;
			}
		}
		
		return true;
	}
}
