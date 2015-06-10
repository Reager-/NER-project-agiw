package DataJungleNER.DataJungleNER;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class WriterEntities {
	private BufferedWriter bw;
public WriterEntities(String path){
	File fout = new File(path);
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(fout);
		this.bw = new BufferedWriter(new OutputStreamWriter(fos));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
}
public void writeEntities(List<String> lista,String url){
	for(String ent:lista){
		try {
			this.bw.write(url+","+ent);
			this.bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
