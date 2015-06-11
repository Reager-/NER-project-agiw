package DataJungleNER.DataJungleNER;

import java.util.List;
import java.util.concurrent.Callable;

import DataJungleNER.DataJungleNER.HtmlCleaner;
import DataJungleNER.DataJungleNER.INamedEntityRecognition;
import DataJungleNER.DataJungleNER.WriterEntities;

public class TaskNER implements Callable<Integer>{
	private WriterEntities we;
	private String url;
	private INamedEntityRecognition ner;
public TaskNER(WriterEntities we,String url,INamedEntityRecognition ner){
	this.we=we;
	this.url=url;
	this.ner=ner;
}
	public Integer call() throws Exception {
		String testo=HtmlCleaner.getAndCleanHtml(url);
		if(testo!=null)
		we.writeEntities(ner.getEntities(testo),url);
		
		return new Integer(0);
	}
	

}
