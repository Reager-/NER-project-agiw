package DataJungleNER.DataJungleNER;

import java.util.Arrays;
import java.util.LinkedList;

import org.jsoup.Jsoup;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.ling.CoreLabel;

public class WrapperStanfordNLP implements INamedEntityRecognition{
	
	private String serializedClassifier;
	private AbstractSequenceClassifier<CoreLabel> classifier;
	
	public WrapperStanfordNLP(){
		
		try {
			serializedClassifier = "training/english.all.3class.nodistsim.crf.ser.gz";
			classifier = CRFClassifier.getClassifier(serializedClassifier);
		} catch (Exception e) {
			System.out.println("Exception in WrapperStanfordNLP constructor");
		}
	}
	

    private	LinkedList<String> getTypeEntitie(String output,String type){
    	LinkedList<String> l = new LinkedList<String>();
    	l.add(type);
    	String[] r1=Jsoup.parse(output).getElementsByTag(type).html().replaceAll("\\r|\\n", "#").split("#");
    	if(!r1[0].equals("")){
    		for(String s:r1){
    			if(!l.contains(s))
    				l.add(s);
    		}
    		      
    	}
    	
    	return l;
    	
    }
    
	public LinkedList<String> getEntities(String html) {
		
		LinkedList<String> result = new LinkedList<String>();
		String output=this.classifier.classifyToString(html, "inlineXML", true); 
	    result.addAll(this.getTypeEntitie(output, "LOCATION"));
	    result.addAll(this.getTypeEntitie(output, "PERSON"));
	    result.addAll(this.getTypeEntitie(output, "ORGANIZATION"));
		return result;
	}
	

	
}