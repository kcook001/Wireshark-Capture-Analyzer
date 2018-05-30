import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tracker {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 2) {
			System.out.println("Incorrect number of arguments.");
			return;
		}
		
		List<String> l1 = load(args[0]);
		List<String> l2 = load(args[1]);
		List<String> results = new ArrayList<>();
		
		for(String candidate : l1) {
			if(l2.contains(candidate)) {
				results.add(candidate);
			}
		}
		
		if(results.isEmpty()) {
			System.out.println("No common MAC addresses found.");
		} else {
			System.out.println("Common MAC addresses found:");
			for(String a : results) {
				System.out.println(a);
			}
		}
		
	}
	
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
