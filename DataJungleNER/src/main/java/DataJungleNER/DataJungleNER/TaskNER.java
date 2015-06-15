package DataJungleNER.DataJungleNER;

import java.util.concurrent.Callable;

import DataJungleNER.DataJungleNER.HtmlCleaner;
import DataJungleNER.DataJungleNER.INamedEntityRecognition;
import DataJungleNER.DataJungleNER.WriterEntities;

public class TaskNER implements Callable<Integer>{
	private WriterEntities we;
	private String url;
	private INamedEntityRecognition ner;
	private double counter;
	
	public TaskNER(WriterEntities we,String url,INamedEntityRecognition ner, double counter){
		this.we=we;
		this.url=url;
		this.ner=ner;
		this.counter=counter;
	}
	
	public Integer call() throws Exception {
		String testo=HtmlCleaner.getAndCleanHtml(url);
		if(testo!=null)
		we.writeEntities(ner.getEntities(testo),url);
		if (counter%10==0)
			System.out.println(counter);

		return new Integer(0);
	}
	

}
