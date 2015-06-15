package DataJungleNER.DataJungleNER;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DualNERexecutor {
	private String urls;
	 private String output1;
	 private String output2;
	 private final static int NCPU =Runtime.getRuntime().availableProcessors();
	 public DualNERexecutor(String urls,String output1,String output2){
		 this.urls=urls;
		 this.output1=output1;
		 this.output2=output2;
		 
	 }
	 public void exec(){
		 ExecutorService pool=Executors.newFixedThreadPool(NCPU);
	 	WriterEntities we1=new WriterEntities(this.output1);
	 	WriterEntities we2=new WriterEntities(this.output2);
	 	BufferedReader br;
	 	
			try {
				br = new BufferedReader(new FileReader(this.urls));
				String line;
		    	INamedEntityRecognition ner1=NERFactory.getIstance().getOpenNLP();
		    	INamedEntityRecognition ner2=NERFactory.getIstance().getStanfordNLP();
		    	double counter = 0; // how many pages have been analyzed
		    	while ((line = br.readLine())!=null) {
		    		counter++;
		    		pool.submit(new DualTaskNER(we1,we2, line, ner1,ner2, counter));
				}
		    	pool.shutdown();
		    	pool.awaitTermination(600000, TimeUnit.SECONDS);
		    	we1.closeWriter();
		    	we2.closeWriter();
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
