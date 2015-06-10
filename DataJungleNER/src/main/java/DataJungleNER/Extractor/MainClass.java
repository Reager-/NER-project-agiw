package DataJungleNER.Extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainClass {
public static void main(String [] args) throws IOException{
	//il main prende la lista dei siti su cui fare le query e poi tramite extractor le fa 
	//	e invoca un writer che scriverà i vari url su un file 
	
		
	
	UrlsWriter wr=new UrlsWriter("C:/Users/Eder/Downloads/NERUrlsExtracted/urls200.txt");
	
	BufferedReader br = new BufferedReader(new FileReader("listofnews.txt"));
	String line;
	String baseUrl = "http://index.commoncrawl.org/CC-MAIN-2015-06-index?url=replace%2F*&output=json" ;
	int i=0;
	while ((line = br.readLine()) != null&&i<20) {
		i++;
		wr.writeUrl(URLsExtractor.extractURLfromJson(baseUrl.replace("replace", line.trim())));
	}
	br.close();
}
}
