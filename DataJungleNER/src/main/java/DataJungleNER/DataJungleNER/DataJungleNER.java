package DataJungleNER.DataJungleNER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import DataJungleNER.Extractor.URLsExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class DataJungleNER 
{
    public static void main( String[] args ) throws BoilerpipeProcessingException, IOException
    {
    	CleanerHtml ch =new CleanerHtml();
    	BufferedReader br = new BufferedReader(new FileReader("listofnews.txt"));
    	String line;
    	while ((line = br.readLine())!=null) {
			String testo=ch.getAndcleanhtml(line);
			NERFactory.getIstance().getOpenNLP().getEntities(testo);
			
		}
    	
    	
    	
     Document doc = Jsoup.connect("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help").get();
     String text = ArticleExtractor.INSTANCE.getText(doc.html());
     System.out.println(text);
     
     WrapperOpenNLP o=new WrapperOpenNLP();
     for(String m:o.getEntities(text)){
    	System.out.println(m); 
     }
     
     System.out.println("Start AlchemyAPI:");
     WrapperAlchemyAPI a=new WrapperAlchemyAPI();
     for(String m:a.getEntities(text)){
    	System.out.println(m); 
     }
     
    }
    
}
