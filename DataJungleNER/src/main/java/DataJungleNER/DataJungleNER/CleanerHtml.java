package DataJungleNER.DataJungleNER;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class CleanerHtml {
public static String getAndcleanhtml(String url){
	Document doc;
	String text="";
	try {
		doc = Jsoup.connect("http://www.10news.com/newsy/apple-is-late-to-vr-and-ar-but-its-acquisitions-could-help").get();
		text = ArticleExtractor.INSTANCE.getText(doc.html());
	} catch (IOException e) {
		e.printStackTrace();
	}catch (BoilerpipeProcessingException e) {
		e.printStackTrace();
	}
	return text;
}
}
