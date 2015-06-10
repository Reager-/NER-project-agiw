package DataJungleNER.Extractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import DataJungleNER.DataJungleNER.CleanerHtml;

public class HtmlGenerator {
public static void main(String []args){
	String line;
	Document doc;
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("target/prova.txt"));
		File fout = new File("target/html.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		while ((line = br.readLine())!=null) {
			String html=CleanerHtml.getAndCleanhtml(line);
			if(html!=null){
			bw.write(line);
			bw.write(html);
			bw.newLine();
			}
			
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("pagina offline");
	}
	
}
}
