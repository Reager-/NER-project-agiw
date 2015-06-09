package DataJungleNER.Extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class UrlsWriter {
private String pathfile;
private BufferedWriter bw;
//costruttore da modificare fa schifo cosi crea un metodo inizialize() privato chiamato nel costruttore
//che restituisce un buffer
public UrlsWriter(String name) {
	this.pathfile=name;
	File fout = new File(this.pathfile);
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(fout);
		this.bw = new BufferedWriter(new OutputStreamWriter(fos));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
}
public void writeUrl(List<String> urls){
	try {
		 
		for (String url:urls) {
			System.out.println(url);
			bw.write(url);
			bw.newLine();
		}
	 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
	
	
}
public void closeWriter(){
	try {
		this.bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
