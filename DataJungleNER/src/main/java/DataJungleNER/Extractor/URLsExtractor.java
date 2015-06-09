package DataJungleNER.Extractor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class URLsExtractor {
	
public static LinkedList<String> extractURLfromJson(String query){
	
	URL url;
	LinkedList<String> urls=new LinkedList<String>();
	try {
		url = new URL(query);
		Scanner scan = new Scanner(url.openStream());
		String objectJson = new String();
		while (scan.hasNext()){
		objectJson = scan.nextLine();
		String urlextracted=JsonParser.takeUrlfromJson(objectJson);
		if(!urls.contains(urlextracted)){
			urls.add(urlextracted);
		}
		}
		scan.close();
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return urls;
	

}
}
