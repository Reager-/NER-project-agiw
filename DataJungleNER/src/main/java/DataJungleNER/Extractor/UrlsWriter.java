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
public UrlsWriter(String name){
	this.pathfile=name;
	
}
public void writeUrl(List<String> urls){
	File fout = new File(this.pathfile);
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
		for (String url:urls) {
			bw.write(url);
			bw.newLine();
		}
	 
		bw.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
 
	
	
}
}
