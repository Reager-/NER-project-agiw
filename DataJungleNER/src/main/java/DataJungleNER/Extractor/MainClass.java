package DataJungleNER.Extractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainClass {
public static void main(String [] args) throws IOException{
//il main prende la lista dei siti su cui fare le query e poi tramite extractor le fa 
//	e invoca un writer che scriverà i vari url su un file
String s = "http://index.commoncrawl.org/CC-MAIN-2015-06-index?url=10news.com%2F*&output=json" ;
UrlsWriter wr=new UrlsWriter("/Users/daniel/Desktop/JsonFileGiw/urls.txt");
wr.writeUrl(URLsExtractor.extractURLfromJson(s));

}
}
