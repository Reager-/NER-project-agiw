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
    

    private	LinkedList<String> getAll(String output){
    	LinkedList<String> l = new LinkedList<String>();
    	l.add("LOCATION");
    	String[] r1=Jsoup.parse(output).getElementsByTag("LOCATION").html().split("/n");
    	if(!r1[0].equals(""))
    		l.addAll(Arrays.asList(r1));
    	l.add("ORGANIZATION");
    	String[] r2=Jsoup.parse(output).getElementsByTag("ORGANIZATION").html().split("/n");
    	if(!r2[0].equals(""))
    		l.addAll(Arrays.asList(r2));
    	l.add("PERSON");
    	String[] r3=Jsoup.parse(output).getElementsByTag("PERSON").html().split("/n");
    	if(!r3[0].equals(""))
    		l.addAll(Arrays.asList(r3));
    	
    	return l;
    	
    }
    
	public LinkedList<String> getEntities(String html) {
		
	
		String output=this.classifier.classifyToString(html, "inlineXML", true); 
		
		return this.getAll(output);
	}
	//da cancellare
public String getEntitiesss(String html) {
		
		
		 //http://stackoverflow.com/questions/28975890/stanford-ner-running
		return this.classifier.classifyToString(html, "inlineXML", true);
	}
	
}