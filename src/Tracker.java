// Wireshark Capture Analyzer
// Keith Cook

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
		String file9 = "RECtest3.csv";
		String file10 = "BStest1.csv";
		String file11 = "BStest2.csv";
		String file12 = "BStest3.csv";
		
		//List of vendors from MAC addresses found in the capture
		Map<String, Integer> vendorsSeen = new HashMap<>();
		
		//List of known vendor MACs, loaded from a file.
		Map<String, String> vendors = vendorLoad("oui.txt");
		
		
		//MAC addresses
		List<String> l1 = load(file1, vendorsSeen, vendors);
		List<String> l2 = load(file2, vendorsSeen, vendors);
		List<String> l3 = load(file3, vendorsSeen, vendors);
		List<String> l4 = load(file4, vendorsSeen, vendors);
		List<String> l5 = load(file5, vendorsSeen, vendors);
		List<String> l6 = load(file6, vendorsSeen, vendors);
		List<String> l7 = load(file7, vendorsSeen, vendors);
		List<String> l8 = load(file8, vendorsSeen, vendors);
		List<String> l9 = load(file9, vendorsSeen, vendors);
		List<String> l10 = load(file10, vendorsSeen, vendors);
		List<String> l11 = load(file11, vendorsSeen, vendors);
		List<String> l12 = load(file12, vendorsSeen, vendors);
		
		//List of MAC addresses found in multiple files
		Map<String, Set<String>> results = new HashMap<>();
		
		//Check each MAC address in each file
		//I got lazy here, if I was going to use this for more than one day I should have abstracted this into a single loop.
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
			compare(l11, candidate, file1, file11, results);
			compare(l12, candidate, file1, file12, results);
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
			compare(l11, candidate, file2, file11, results);
			compare(l12, candidate, file2, file12, results);
		}
		
		for(String candidate : l3) {
			compare(l4, candidate, file3, file4, results);
			compare(l5, candidate, file3, file5, results);
			compare(l6, candidate, file3, file6, results);
			compare(l7, candidate, file3, file7, results);
			compare(l8, candidate, file3, file8, results);
			compare(l9, candidate, file3, file9, results);
			compare(l10, candidate, file3, file10, results);
			compare(l11, candidate, file3, file11, results);
			compare(l12, candidate, file3, file12, results);
		}
		
		for(String candidate : l4) {
			compare(l5, candidate, file4, file5, results);
			compare(l6, candidate, file4, file6, results);
			compare(l7, candidate, file4, file7, results);
			compare(l8, candidate, file4, file8, results);
			compare(l9, candidate, file4, file9, results);
			compare(l10, candidate, file4, file10, results);
			compare(l11, candidate, file4, file11, results);
			compare(l12, candidate, file4, file12, results);
		}
		
		for(String candidate : l5) {
			compare(l6, candidate, file5, file6, results);
			compare(l7, candidate, file5, file7, results);
			compare(l8, candidate, file5, file8, results);
			compare(l9, candidate, file5, file9, results);
			compare(l10, candidate, file5, file10, results);
			compare(l11, candidate, file5, file11, results);
			compare(l12, candidate, file5, file12, results);
		}
		
		for(String candidate : l6) {
			compare(l7, candidate, file6, file7, results);
			compare(l8, candidate, file6, file8, results);
			compare(l9, candidate, file6, file9, results);
			compare(l10, candidate, file6, file10, results);
			compare(l11, candidate, file6, file11, results);
			compare(l12, candidate, file6, file12, results);
		}
		
		for(String candidate : l7) {
			compare(l8, candidate, file7, file8, results);
			compare(l9, candidate, file7, file9, results);
			compare(l10, candidate, file7, file10, results);
			compare(l11, candidate, file7, file11, results);
			compare(l12, candidate, file7, file12, results);
		}
		
		for(String candidate : l8) {
			compare(l9, candidate, file8, file9, results);
			compare(l10, candidate, file8, file10, results);
			compare(l11, candidate, file8, file11, results);
			compare(l12, candidate, file8, file12, results);
		}
		
		for(String candidate : l9) {
			compare(l10, candidate, file9, file10, results);
			compare(l11, candidate, file9, file11, results);
			compare(l12, candidate, file9, file12, results);
		}
		
		for(String candidate : l10) {
			compare(l11, candidate, file10, file11, results);
			compare(l12, candidate, file10, file12, results);
		}
		
		for(String candidate : l11) {
			compare(l12, candidate, file11, file12, results);
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
		
		
		Map<String, Integer> sorted = sortByValue(vendorsSeen);
		int users = 0;
		
		
		System.out.println("\n\nVendor Frequency:");
		for(String v : sorted.keySet()) {
			System.out.println(v + " : " + sorted.get(v));
			users += sorted.get(v);
		}
		System.out.println("\nDistinct users: " + users);
		System.out.println("Distinct vendor numbers: " + sorted.keySet().size());
		
		/*
		System.out.println("\n\n Known vendor list:");
		for(String v : vendors.keySet()) {
			System.out.println(v + " : " + vendors.get(v));
		}
		*/
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
	public static List<String> load(String filename, Map<String, Integer> vendorsSeen, Map<String, String> vendors) throws FileNotFoundException {
		
		List<String> list = new ArrayList<>();
		
		String line;
		String mac;
		String v;
		String vname;
		
		Scanner sc = new Scanner(new File(filename)).useDelimiter("\\n");
		
		while(sc.hasNext()) {
			line = sc.nextLine();
			if(line.length() > 17 && !line.startsWith("BSSID") && !line.startsWith("Station")) {
				mac = line.substring(0, 17);
				list.add(mac);
				v = mac.substring(0, 8);
				vname = vendors.get(v);
				if(vname != null) {
					if(vendorsSeen.containsKey(vname))
						vendorsSeen.put(vname, vendorsSeen.get(vname) + 1);
					else
						vendorsSeen.put(vname, 1);
				} else {
					if(vendorsSeen.containsKey("Unknown"))
						vendorsSeen.put("Unknown", vendorsSeen.get("Unknown") + 1);
					else
						vendorsSeen.put("Unknown", 1);
				}
			}
		}
		
		sc.close();
		return list;
	}
	
	@SuppressWarnings("resource")
	public static Map<String, String> vendorLoad(String filename) throws FileNotFoundException {
		
		Map<String, String> results = new HashMap<>();
		String line;
		String mac;
		String vendor;
		//int count = 0;
		
		Scanner sc = new Scanner(new File(filename), "UTF-8").useDelimiter("\\n");
		
		while(sc.hasNext()) {
			line = sc.nextLine();
			//if(count%6 == 1) {
			if(line.matches("^[0-9A-F]{2}-.*")) {
				mac = line.substring(0, 8);
				mac = mac.replaceAll("-", ":");
				vendor = line.substring(18);
				if(!vendor.isEmpty())
					results.put(mac, vendor);
			}
			//count++;
		}
		//System.out.println(count);
		
		sc.close();
		return results;
	}
	
	// Sort for Map<String, Integer>
	// Not mine, found online.
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
}
