package main;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class BasicUI {
	public static final String saveFile = "./saveFiles/entries.chris";
	TreeMap<Entry, Entry> entries;
	Scanner sc;
	
	public BasicUI(Scanner sc) {
		entries = new TreeMap<Entry, Entry>((a, b) -> a.name.compareTo(b.name));
		this.sc = sc;
	}
	
	public void run() {
		while (true) {
			System.out.print("0. Prints a full entry\n"
								+ "1. Print all entries\n"
								+ "2. Add an entry\n"
								+ "3. Remove an entry\n"
								+ "4. Search for an entry\n"
								+ "5. Filter entries by tag\n"
								+ "6. Filter entries by key in pair\n"
								+ "7. Filter entries by a pair\n"
								+ "8. Filter entries by a pair, where the value is a number\n"
								+ "\n"
								+ "H. Help\n"
								+ "E. Exit\n"
								+ "\n"
								+ "Choose an option: ");
			
			char choice = sc.nextLine().charAt(0);
			
			buffer();
			
			switch (choice) {
			case '0':
				printEntry();
				break;
			case '1':
				printAllEntries();
				break;
			case '2':
				addEntry();
				break;
			case '3':
				removeEntry();
				break;
			case '4':
				searchEntry();
				break;
			case '5':
				filterByTag();
				break;
			case '6':
				filterByKey();
				break;
			case '7':
				filterByPair();
				break;
			case '8': 
				filterByValueAsNumber();
				break;
			case 'h': case 'H':
				help();
				break;
			case 'e': case 'E':
				System.exit(0);
			default:
				System.out.println("Please enter a valid result.");
			}
			
			buffer();
		}
	}
	
	private void help() {
		System.out.print("Welcome to the Information storer!\n"
				+ "\n"
				+ "This implementation stores alll information as \"entries\"\n"
				+ "that include 3 different ways of storing information,\n"
				+ "all of which can be used to search/filter:\n"
				+ "\n"
				+ "Name:\n"
				+ "\tThe name of the entry.\n"
				+ "\n"
				+ "Tags:\n"
				+ "\tA list of short bits of information about the object.\n"
				+ "\tExample usage cases of tags would be for\n"
				+ "\tthe industry of a buisness or its resources.\n"
				+ "\n"
				+ "Pairs:\n"
				+ "\tA list of key-value pairs of information.\n"
				+ "\tExample usage cases of pairs would be for\n"
				+ "\t a money balance or contact information\n");
	}

	public void printEntry() {
		System.out.print("Enter the name of object to remove: ");
		String name = sc.nextLine();
		
		var key = new Entry(name);
		
		if (!entries.containsKey(key)) {
			System.out.println("Entry does not exist (try searching for it first?)");
			return;
		}
		
		System.out.print(entries.get(key).toFullString());
	}

	public void printAllEntries() {
		for (Entry e : entries.keySet()) {
			System.out.println(e);
		}
	}
	
	public void addEntry() {
		System.out.print("Enter a name for the new object: ");
		String name = sc.nextLine().toLowerCase();

		System.out.println();
		var tags = new ArrayList<String>();
		while (true) {
			System.out.print("Keep entering tags (hit enter to stop): ");
			String tag = sc.nextLine();
			
			if (tag.contains(",")) {
				System.out.println("Cannot include \",\" in tag!");
				continue;
			}
			
			System.out.println();
			if (tag.isBlank()) {
				break;
			}
			
			tags.add(tag.toLowerCase());
		}
		
		System.out.println();
		var pairs = new TreeMap<String, String>();
		while (true) {
			System.out.print("Keep entering pairs. Enter a key (hit enter to stop): ");
			String key = sc.nextLine();
			
			if (key.contains(",") || key.contains("=")) {
				System.out.println("Cannot include \",\" or \"=\" in a pair!");
				continue;
			}
			else if (key.isBlank()) {
				break;
			}
			
			System.out.print("Enter a value (hit enter to abort): ");
			String value = sc.nextLine();
			
			System.out.println();
			
			if (value.contains(",") || value.contains("=")) {
				System.out.println("Cannot include \",\" or \"=\" in a pair!");
				continue;
			}
			else if (value.isBlank()) {
				break;
			}
			
			pairs.put(key.toLowerCase(), value.toLowerCase());
		}
		
		Entry e = new Entry(name, tags, pairs);
		entries.put(e, e);
		System.out.println("\n\nSucessfully added entry.");
	}
	
	public void removeEntry() {
		System.out.print("Enter the name of object to remove: ");
		String name = sc.nextLine();
		
		var dummy = new Entry(name);
		
		if (!entries.containsKey(dummy)) {
			System.out.println("Entry does not exist (try searching for it first?)");
			return;
		}
		
		entries.remove(dummy);
		System.out.print("Entry removed sucessfully.");
	}

	public void searchEntry() {
		System.out.print("Enter the search: ");
		String input = sc.nextLine();
		
		ArrayList<Entry> found = new ArrayList<Entry>();
		for (Entry e : entries.keySet()) {
			if (e.nameContains(input)) {
				found.add(e);
			}
		}
		
		if (found.isEmpty()) {
			System.out.print("No elements found (check for spelling mistakes?)");
			return;
		}
		
		System.out.print("Found " + found.size() + " entries, print them all? (Y/N):");
		char choice = sc.nextLine().charAt(0);
		
		if (choice == 'y' || choice == 'Y') {
			for (Entry e : found) {
				System.out.println(e);
			}
		}
	}

	private void filterByTag() {
		System.out.print("Enter the search tag: ");
		String input = sc.nextLine();
		
		ArrayList<Entry> found = new ArrayList<Entry>();
		for (Entry e : entries.keySet()) {
			if (e.nameContains(input)) {
				found.add(e);
			}
		}
		
		if (found.isEmpty()) {
			System.out.print("No elements found (check for spelling mistakes?)");
			return;
		}
		
		System.out.print("Found " + found.size() + " entries, print them all? (Y/N):");
		char choice = sc.nextLine().charAt(0);
		
		if (choice == 'y' || choice == 'Y') {
			for (Entry e : found) {
				System.out.println(e);
			}
		}
	}

	private void filterByKey() {
		System.out.print("Enter the search key: ");
		String key = sc.nextLine();
		
		ArrayList<Entry> found = new ArrayList<Entry>();
		for (Entry e : entries.keySet()) {
			if (e.pairs.containsKey(key)) {
				found.add(e);
			}
		}
		
		if (found.isEmpty()) {
			System.out.print("No elements found (check for spelling mistakes?)");
			return;
		}
		
		System.out.print("Found " + found.size() + " entries, print them all? (Y/N):");
		char choice = sc.nextLine().charAt(0);
		
		if (choice == 'y' || choice == 'Y') {
			for (Entry e : found) {
				System.out.println(e);
			}
		}
	}
	
	private void filterByPair() {
		System.out.print("Enter the search key: ");
		String key = sc.nextLine();
		
		System.out.print("Enter the search value: ");
		String value = sc.nextLine();
		double num = tryParseDouble(value);
		
		if (num != Double.MIN_VALUE) {
			System.out.print("Number detected! Switch to filter by number value? (Y/N):");
			char choice = sc.nextLine().charAt(0);
			
			if (choice == 'y' || choice == 'Y') {
				filterByValueAsNumber(key, num);
				return;
			}
		}
		
		ArrayList<Entry> found = new ArrayList<Entry>();
		for (Entry e : entries.keySet()) {
			if (e.pairs.get(key).equals(value)) {
				found.add(e);
			}
		}
		
		if (found.isEmpty()) {
			System.out.print("No elements found (check for spelling mistakes?)");
			return;
		}
		
		System.out.print("Found " + found.size() + " entries, print them all? (Y/N):");
		char choice = sc.nextLine().charAt(0);
		
		if (choice == 'y' || choice == 'Y') {
			for (Entry e : found) {
				System.out.println(e);
			}
		}
	}
	
	private void filterByValueAsNumber() {
		System.out.print("Enter the search key: ");
		String key = sc.nextLine();
		
		System.out.print("Enter the valber search value: ");
		double val = tryParseDouble(sc.nextLine());
		
		while (val == Double.MIN_VALUE) {
			System.out.print("Please enter a valid valber:");
			val = tryParseDouble(sc.nextLine());
		}
		
		filterByValueAsNumber(key, val);
	}
	
	private void filterByValueAsNumber(String key, double val) {
		boolean hasPickedChoice = false;
		String op = "";
		
		while (!hasPickedChoice) {
			System.out.print("\neq. Equal to\n"
								+ "lt. Less than\n"
								+ "gt. Greater than\n"
								+ "lt. Less than or equal to\n"
								+ "gt. Greater than or equal to\n"
								+ "\n"
								+ "Choose an option: ");
			
			op = sc.nextLine().substring(0, 2).toLowerCase();
			
			switch (op) {
			case "eq": case "lt": case "gt": case "le": case "ge":
				hasPickedChoice = true;
				break;
			default:
				System.out.println("Please enter a valid result.");
			}
		}
		
		ArrayList<Entry> found = new ArrayList<Entry>();
		
		switch (op) {
		case "eq":
			for (Entry e : entries.keySet()) {
				double num = tryParseDouble(e.pairs.get(key));
				
				if (num == val)
					found.add(e);
			}
			break;
		case "lt":
			for (Entry e : entries.keySet()) {
				double num = tryParseDouble(e.pairs.get(key));
				
				if (num < val)
					found.add(e);
			}
			break;
		case "gt":
			for (Entry e : entries.keySet()) {
				double num = tryParseDouble(e.pairs.get(key));
				
				if (num > val)
					found.add(e);
			}
			break;
		case "le":
			for (Entry e : entries.keySet()) {
				double num = tryParseDouble(e.pairs.get(key));
				
				if (num <= val)
					found.add(e);
			}
			break;
		case "ge":
			for (Entry e : entries.keySet()) {
				double num = tryParseDouble(e.pairs.get(key));
				
				if (num >= val)
					found.add(e);
			}
			break;
		}
		
		if (found.isEmpty()) {
			System.out.print("No elements found (check for spelling mistakes?)");
			return;
		}
		
		System.out.print("Found " + found.size() + " entries, print them all? (Y/N):");
		char choice = sc.nextLine().charAt(0);
		
		if (choice == 'y' || choice == 'Y') {
			for (Entry e : found) {
				System.out.println(e);
			}
		}
	}

	public double tryParseDouble(String value) {
	    try {
	        return Double.parseDouble(value);
	    } catch (NumberFormatException e) {
	        return Double.MIN_VALUE;
	    }
	}
	
	private void buffer() {
		System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
	}

	public void save() throws FileNotFoundException {
		// yes the file extention is .chris
		// no i dont want to implement this as json
		
		// TODO implement as json
		
		var out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(saveFile)));
		
		for (Entry e : entries.keySet()){
			out.println(e.name);
			
			StringBuilder temp = new StringBuilder();
			
			if (!e.tags.isEmpty()) {
				for (String tag : e.tags) {
					temp.append(tag + ",");
				}
				
				temp.deleteCharAt(temp.length() - 1);
				out.println(temp);
			}
			else {
				out.println();
			}
			
			if (!e.pairs.isEmpty()) {
				temp.setLength(0);
				for (var entry : e.pairs.entrySet()) {
					temp.append(entry.getKey() + "=" + entry.getValue() + ",");
				}
				temp.deleteCharAt(temp.length() - 1);
				out.println(temp);
			}
			else {
				out.println();
			}
		}
		
		out.flush();
		out.close();
	}
	
	public void load() throws IOException {
		// yes the file extention is .chris
		// no i dont want to implement this as json
		
		var lines = Files.readAllLines(Paths.get(saveFile));
		
		for (int entryNumber = 0; entryNumber < lines.size(); entryNumber += 3) {
			String name = lines.get(entryNumber);
			
			List<String> tags = Arrays.asList(lines.get(entryNumber + 1).split(","));
			
			String[] pairsAsArray = lines.get(entryNumber + 2).split("=");
			var pairs = new TreeMap<String, String>();
			
			for (int i = 0; i < pairsAsArray.length; i += 2) {
				pairs.put(pairsAsArray[i], pairsAsArray[i + 1]);
			}
			
			Entry entry = new Entry(name, tags, pairs);
			entries.put(entry, entry);
		}
	}
}
