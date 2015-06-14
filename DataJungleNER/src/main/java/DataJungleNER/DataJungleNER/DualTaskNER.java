package DataJungleNER.DataJungleNER;


import java.util.concurrent.Callable;

import DataJungleNER.DataJungleNER.HtmlCleaner;
import DataJungleNER.DataJungleNER.INamedEntityRecognition;
import DataJungleNER.DataJungleNER.WriterEntities;

public class DualTaskNER implements Callable<Integer>{
	private WriterEntities we1;
	private WriterEntities we2;
	private String url;
	private INamedEntityRecognition ner1;
	private INamedEntityRecognition ner2;
	private double counter;
	
	public DualTaskNER(WriterEntities we1,WriterEntities we2,String url,INamedEntityRecognition ner1,INamedEntityRecognition ner2, double counter){
		this.we1=we1;
		this.we2=we2;
		this.url=url;
		this.ner1=ner1;
		this.ner2=ner2;
		this.counter=counter;
	}
	
	public Integer call() throws Exception {
		String testo=HtmlCleaner.getAndCleanHtml(url);
		if(testo!=null){
		we1.writeEntities(ner1.getEntities(testo),url);
		we2.writeEntities(ner2.getEntities(testo), url);
		if (counter%10==0)
			System.out.println(counter);
		}
		return new Integer(0);
	}
	

}