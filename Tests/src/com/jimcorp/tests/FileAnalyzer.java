package com.jimcorp.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileAnalyzer {

	public static void main(String[] args) {

		String path = "C:\\Temp\\test.txt";
		
		File file = new File(path);
		Scanner scan;
		
		int maxLength = 0;
		List<String> lines = new ArrayList<String>();
		
		try {
			scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				
				String line = scan.nextLine();
				int length = line.length();
				if(length >= maxLength) {
					if(! (length == maxLength)) {
						lines.clear();
					}
					maxLength = length;
					lines.add(line);
				}
			}
			
			System.out.printf("The following %s line(s) have %s characters, and are the longest lines in the file:%n", 
					lines.size(), maxLength);
			for(String line : lines) {
				System.out.println(" - " + line);
			}
		} catch (FileNotFoundException e) {
			
			System.err.println(String.format("ERROR! Unable to read file '%s'. Reason: %s%n", path, e.getMessage()));
		}
	}

}
