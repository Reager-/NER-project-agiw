package DataJungleNER.Extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class MainClass {
public static void main(String [] args) throws IOException{
//il main prende la lista dei siti su cui fare le query e poi tramite extractor le fa 
//	e invoca un writer che scriverà i vari url su un file 

	

UrlsWriter wr=new UrlsWriter("/Users/daniel/Desktop/JsonFileGiw/urls200.txt");
BufferedReader br = new BufferedReader(new FileReader("/Users/daniel/Desktop/JsonFileGiw/listadellenews.txt"));
String line;
String baseUrl = "http://index.commoncrawl.org/CC-MAIN-2015-06-index?url=replace%2F*&output=json" ;
int i=0;
while ((line = br.readLine()) != null&&i<100) {
	i++;
	wr.writeUrl(URLsExtractor.extractURLfromJson(baseUrl.replace("replace", line.trim())));
}

}
}
