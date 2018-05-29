import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tracker {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 2) {
			System.out.println("Incorrect number of arguments.");
			return;
		}
		
		String file1 = args[0];
		String file2 = args[1];
		
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		List<String> results = new ArrayList<>();
		
		String line;
		String mac;
		
		Scanner sc1 = new Scanner(new File(file1)).useDelimiter("\\n");
		Scanner sc2 = new Scanner(new File(file2)).useDelimiter("\\n");
		
		while(sc1.hasNext()) {
			//candidate = inFile1.next();
			line = sc1.nextLine();
			if(line.length() > 17 && !line.startsWith("BSSID") && !line.startsWith("Station")) {
				mac = line.substring(0, 17);
				l1.add(mac);
			}
		}
		
		while(sc2.hasNext()) {
			//candidate = inFile1.next();
			line = sc2.nextLine();
			if(line.length() > 17 && !line.startsWith("BSSID") && !line.startsWith("Station")) {
				mac = line.substring(0, 17);
				l2.add(mac);
			}
		}
		
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
		
		sc1.close();
		sc2.close();
	}
	
}
