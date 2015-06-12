package DataJungleNER.DataJungleNER;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.ling.CoreLabel;

public class WrapperStanfordNLP implements INamedEntityRecognition{
	
	private String serializedClassifier;
	//private AbstractSequenceClassifier<CoreLabel> classifier;
	private NERClassifierCombiner classifier;
	
	public WrapperStanfordNLP(){
		
		try {
			serializedClassifier = "training/english.all.3class.nodistsim.crf.ser.gz";
			//classifier = CRFClassifier.getClassifier(serializedClassifier);
			classifier = new NERClassifierCombiner(false, false, serializedClassifier);
		} catch (Exception e) {
			System.out.println("Exception in WrapperStanfordNLP constructor");
		}
	}
    

    private	LinkedList<String> getAll(String output){
    	LinkedList<String> result = new LinkedList<String>();
    	result.add("ORGANIZATION");
    	Iterator<Element> it = Jsoup.parse(output).getElementsByTag("ORGANIZATION").iterator();
    	while (it.hasNext()){
    		String entity = it.next().text();
    		if (!result.contains(entity)){
    			result.add(entity);
    		}
    	}
    	result.add("PERSON");
    	Iterator<Element> it2 = Jsoup.parse(output).getElementsByTag("PERSON").iterator();
    	while (it2.hasNext()){
    		String entity = it2.next().text();
    		if (!result.contains(entity)){
    			result.add(entity);
    		}
    	}
    	result.add("LOCATION");
    	Iterator<Element> it3 = Jsoup.parse(output).getElementsByTag("LOCATION").iterator();
    	while (it3.hasNext()){
    		String entity = it3.next().text();
    		if (!result.contains(entity)){
    			result.add(entity);
    		}
    	}
    	
    	return result;
    	
    }
    
	public LinkedList<String> getEntities(String html) {
		
	
		String output=this.classifier.classifyToString(html, "inlineXML", true);
		return this.getAll(output);
	}
	
}