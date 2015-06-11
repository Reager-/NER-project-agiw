package DataJungleNER.DataJungleNER;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataJungleNER 
{
    public static void main( String[] args ) {
    	WriterEntities we=new WriterEntities("target/entità2.txt");
    	BufferedReader br;
    	
		try {
			br = new BufferedReader(new FileReader("target/prova.txt"));
			String line;
	    	INamedEntityRecognition ner=NERFactory.getIstance().getOpenNLP();
	    	int pageCounter = 1;
	    	while ((line = br.readLine())!=null) {
	    		System.out.println(pageCounter);
	    		pageCounter++;
				String testo=CleanerHtml.getAndcleanhtml(line);
				we.writeEntities(ner.getEntities(testo),line);
				
			}
	    	we.closeWriter();
	    	br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

     /*Document doc = Jsoup.connect("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help").get();

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
     */
    }
    
}
