package DataJungleNER.DataJungleNER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class WrapperOpenNLP implements INamedEntityRecognition{
	private NameFinderME getNameFinder(String dataTraning){
		InputStream is;
		TokenNameFinderModel model;
		NameFinderME nameFinder;
		try {
			is = new FileInputStream(dataTraning);
			model = new TokenNameFinderModel(is);
			nameFinder = new NameFinderME(model);
			is.close();
			return nameFinder;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         return null;

	}
    private String[] tokenizer(String text){
    	InputStream is;
        TokenizerModel tm;
            try {
				is = new FileInputStream("traning/en-token.bin");
				tm = new TokenizerModel(is);
				Tokenizer tokenizer = new TokenizerME(tm);
	            String [] tokens = tokenizer.tokenize(text);
	            return tokens;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
            
        
    }
    	
    	
    	
    	
    
	private LinkedList<String> getOrganization(String []token) {
		    NameFinderME nameFinder=this.getNameFinder("traning/en-ner-organization.bin");
            Span sp[] = nameFinder.find(token);
            String a[] = Span.spansToStrings(sp, token);
            LinkedList<String> org=new LinkedList<String>();
            for (int j = 0; j < a.length; j++) {
            	String entity=sp[j].getType()+","+a[j];
            	if(!org.contains(entity)){
                org.add(entity);
            	}

            }
            return org;
		
	}

	private LinkedList<String> getLocation(String []token) {
		NameFinderME namefinder=this.getNameFinder("traning/en-ner-location.bin");
        Span sp[] = namefinder.find(token);
        String a[] = Span.spansToStrings(sp, token);
        LinkedList<String> location=new LinkedList<String>();
        for (int j = 0; j < a.length; j++) {
        	String entity=sp[j].getType()+","+a[j];
        	if(!location.contains(entity))
            location.add(entity);
        	}

	return location;
	}

	private LinkedList<String> getPersonName(String [] token) {
		    NameFinderME namefinder=this.getNameFinder("traning"
		    		+ "/en-ner-person.bin");
            Span sp[] = namefinder.find(token);
            String a[] = Span.spansToStrings(sp, token);
            LinkedList<String> personName=new LinkedList<String>();
            for (int j = 0; j < a.length; j++) {
            	String entity=sp[j].getType()+","+a[j];
            	if(!personName.contains(entity))
                personName.add(entity);
            	}

		return personName;
	}
	
	
	public LinkedList<String> getEntities(String html) {
		LinkedList<String> entities=new LinkedList<String>();
		String []token =this.tokenizer(html);
		entities.addAll(this.getOrganization(token));
		entities.addAll(this.getPersonName(token));
		entities.addAll(this.getLocation(token));
		
		return entities;
	}
}
