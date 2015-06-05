package DataJungleNER.DataJungleNER;

import java.net.MalformedURLException;
import java.net.URL;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class DataJungleNER 
{
    public static void main( String[] args ) throws MalformedURLException, BoilerpipeProcessingException
    {
     String s="cao";
     System.out.println("ultima prova di conflitto");
     URL url = new URL("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help");
     String text = ArticleExtractor.INSTANCE.getText(url);
     System.out.println(text);
    }
}
