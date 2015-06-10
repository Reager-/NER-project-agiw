package DataJungleNER.DataJungleNER;

import java.util.LinkedList;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.ling.CoreLabel;

public class WrapperStanfordNLP implements INamedEntityRecognition{
	
	private String serializedClassifier;
	private AbstractSequenceClassifier<CoreLabel> classifier;
	
	public WrapperStanfordNLP(){
		
		try {
			serializedClassifier = "traning/english.all.3class.nodistsim.crf.ser.gz";
			classifier = CRFClassifier.getClassifier(serializedClassifier);
		} catch (Exception e) {
			System.out.println("Exception in WrapperStanfordNLP constructor");
		}
	}

	public LinkedList<String> getEntities(String html) {
		
		LinkedList<String> result = new LinkedList<String>();
		this.classifier.classifyToString(html, "xml", true);
		 
		return result;
	}
	
}