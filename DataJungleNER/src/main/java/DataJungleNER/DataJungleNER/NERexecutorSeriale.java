package DataJungleNER.DataJungleNER;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NERexecutorSeriale {
	private String urls;
	 private String output;
	 
	 public NERexecutorSeriale(String urls,String output){
		 this.urls=urls;
		 this.output=output;
		 
	 }
	 public void exec(){
		 WriterEntities we=new WriterEntities(this.output);
	    	BufferedReader br;
	    	
			try {
				br = new BufferedReader(new FileReader(this.urls));
				String line;
		    	INamedEntityRecognition ner=NERFactory.getIstance().getOpenNLP();
		    	while ((line = br.readLine())!=null) {
					String testo=HtmlCleaner.getAndCleanHtml(line);
					if(testo!=null)
					we.writeEntities(ner.getEntities(testo),line);
					
				}
		    	we.closeWriter();
		    	br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	     
	 }

}
