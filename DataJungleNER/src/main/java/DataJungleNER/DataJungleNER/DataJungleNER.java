package DataJungleNER.DataJungleNER;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class DataJungleNER 
{
    public static void main( String[] args ) throws BoilerpipeProcessingException, IOException
    {
     String s="cao";
     System.out.println("ultima prova di conflitto");
     
     URL url = new URL("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help");
     Document doc = Jsoup.connect("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help").get();
     String text = ArticleExtractor.INSTANCE.getText(doc.html());
     System.out.println(text);
    }
}
