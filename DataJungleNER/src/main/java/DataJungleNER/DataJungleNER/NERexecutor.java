package DataJungleNER.DataJungleNER;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import DataJungleNER.DataJungleNER.INamedEntityRecognition;
import DataJungleNER.DataJungleNER.NERFactory;
import DataJungleNER.DataJungleNER.WriterEntities;

public class NERexecutor {
 private String urls;
 private String output;
 private final static int NCPU =Runtime.getRuntime().availableProcessors();
 public NERexecutor(String urls,String output){
	 this.urls=urls;
	 this.output=output;
	 
 }
 public void exec(){
	 ExecutorService pool=Executors.newFixedThreadPool(NCPU);
 	WriterEntities we=new WriterEntities(this.output);
 	BufferedReader br;
 	
		try {
			br = new BufferedReader(new FileReader(this.urls));
			String line;
	    	INamedEntityRecognition ner=NERFactory.getIstance().getStanfordNLP();
	    	while ((line = br.readLine())!=null) {
	    		pool.submit(new TaskNER(we, line, ner));
				
			}
	    	pool.shutdown();
	    	pool.awaitTermination(600000, TimeUnit.SECONDS);
	    	we.closeWriter();
	    	br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	 
 }
}
