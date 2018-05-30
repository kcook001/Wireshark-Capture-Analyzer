import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Tracker {

	//Generate a list of which MAC addresses were found in multiple WireShark capture files and output where they were found.
	public static void main(String[] args) throws FileNotFoundException {
		
		//File names
		String file1 = "EBtest1.csv";
		String file2 = "EBtest2.csv";
		String file3 = "EBtest3.csv";
		String file4 = "LIBtest1.csv";
		String file5 = "LIBtest2.csv";
		String file6 = "LIBtest3.csv";
		String file7 = "RECtest1.csv";
		String file8 = "RECtest2.csv";
		String file9 = "BStest2.csv";
		String file10 = "BStest3.csv";
		
		//MAC addresses
		List<String> l1 = load(file1);
		List<String> l2 = load(file2);
		List<String> l3 = load(file3);
		List<String> l4 = load(file4);
		List<String> l5 = load(file5);
		List<String> l6 = load(file6);
		List<String> l7 = load(file7);
		List<String> l8 = load(file8);
		List<String> l9 = load(file9);
		List<String> l10 = load(file10);
		
		//List of MAC addresses found in multiple files
		Map<String, Set<String>> results = new HashMap<>();
		
		//Check each MAC address in each file
		for(String candidate : l1) {
			compare(l2, candidate, file1, file2, results);
			compare(l3, candidate, file1, file3, results);
			compare(l4, candidate, file1, file4, results);
			compare(l5, candidate, file1, file5, results);
			compare(l6, candidate, file1, file6, results);
			compare(l7, candidate, file1, file7, results);
			compare(l8, candidate, file1, file8, results);
			compare(l9, candidate, file1, file9, results);
			compare(l10, candidate, file1, file10, results);
		}
		
		for(String candidate : l2) {
			compare(l3, candidate, file2, file3, results);
			compare(l4, candidate, file2, file4, results);
			compare(l5, candidate, file2, file5, results);
			compare(l6, candidate, file2, file6, results);
			compare(l7, candidate, file2, file7, results);
			compare(l8, candidate, file2, file8, results);
			compare(l9, candidate, file2, file9, results);
			compare(l10, candidate, file2, file10, results);
		}
		
		for(String candidate : l3) {
			compare(l4, candidate, file3, file4, results);
			compare(l5, candidate, file3, file5, results);
			compare(l6, candidate, file3, file6, results);
			compare(l7, candidate, file3, file7, results);
			compare(l8, candidate, file3, file8, results);
			compare(l9, candidate, file3, file9, results);
			compare(l10, candidate, file3, file10, results);
		}
		
		for(String candidate : l4) {
			compare(l5, candidate, file4, file5, results);
			compare(l6, candidate, file4, file6, results);
			compare(l7, candidate, file4, file7, results);
			compare(l8, candidate, file4, file8, results);
			compare(l9, candidate, file4, file9, results);
			compare(l10, candidate, file4, file10, results);
		}
		
		for(String candidate : l5) {
			compare(l6, candidate, file5, file6, results);
			compare(l7, candidate, file5, file7, results);
			compare(l8, candidate, file5, file8, results);
			compare(l9, candidate, file5, file9, results);
			compare(l10, candidate, file5, file10, results);
		}
		
		for(String candidate : l6) {
			compare(l7, candidate, file6, file7, results);
			compare(l8, candidate, file6, file8, results);
			compare(l9, candidate, file6, file9, results);
			compare(l10, candidate, file6, file10, results);
		}
		
		for(String candidate : l7) {
			compare(l8, candidate, file7, file8, results);
			compare(l9, candidate, file7, file9, results);
			compare(l10, candidate, file7, file10, results);
		}
		
		for(String candidate : l8) {
			compare(l9, candidate, file8, file9, results);
			compare(l10, candidate, file8, file10, results);
		}
		
		for(String candidate : l9) {
			compare(l10, candidate, file9, file10, results);
		}
		
		//Output results
		if(results.isEmpty()) {
			System.out.println("No common MAC addresses found.");
		} else {
			System.out.println( results.keySet().size() + " common MAC addresses found:");
			for(String a : results.keySet()) {
				if(results.get(a) == null) {
					System.out.println(a);
				} else {
					
					System.out.println(a);
					for(String b : results.get(a)){
					System.out.println("\t"+b);
				}
			}
		}
		}
	}
	
	//Check whether a MAC address from one file is in another.  If it is, add it and the names of the files it was found in to the results list. 
	public static void compare(List<String> list, String candidate, String file1, String file2, Map<String, Set<String>> results) {
		if(list.contains(candidate)) {
			if(results.containsKey(candidate)) {
				results.get(candidate).add(file1);
				results.get(candidate).add(file2);
			} else {
				results.put(candidate, new HashSet<>());
				results.get(candidate).add(file1);
				results.get(candidate).add(file2);
			}
		}
	}
	
	//Load MAC addresses from file to List data structure.
	@SuppressWarnings("resource")
	public static List<String> load(String filename) throws FileNotFoundException {
		
		List<String> list = new ArrayList<>();
		
		String line;
		String mac;
		
		Scanner sc = new Scanner(new File(filename)).useDelimiter("\\n");
		
		while(sc.hasNext()) {
			//candidate = inFile1.next();
			line = sc.nextLine();
			if(line.length() > 17 && !line.startsWith("BSSID") && !line.startsWith("Station")) {
				mac = line.substring(0, 17);
				list.add(mac);
			}
		}
		
		sc.close();
		return list;
	}
	
}
