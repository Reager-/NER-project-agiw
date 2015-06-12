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
	private NameFinderME organiziationModel;
	private NameFinderME personModel;
	private NameFinderME locationModel;
	private TokenizerME  tokenizerModel;
	public WrapperOpenNLP(){
		this.organiziationModel=this.getNameFinder("training/en-ner-organization.bin");
		this.personModel=this.getNameFinder("training/en-ner-person.bin");
		this.locationModel=this.getNameFinder("training/en-ner-location.bin");
		this.tokenizerModel=this.getTokenizerModel("training/en-token.bin");
		
	} 
	private TokenizerME getTokenizerModel(String dataTraining){
		InputStream is;
        TokenizerModel tm;
		try {
			is = new FileInputStream(dataTraining);
			tm = new TokenizerModel(is);
			return new TokenizerME(tm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
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
            
	            String [] tokens = this.tokenizerModel.tokenize(text);
			
			return tokens;
            
        
    }
    	
    	
    	
    	
    
	/*private LinkedList<String> getOrganization(String []token) {
            Span sp[] =this.organiziationModel.find(token);
            String a[] = Span.spansToStrings(sp, token);
            LinkedList<String> org=new LinkedList<String>();
            for (int j = 0; j < a.length; j++) {
            	String entity=sp[j].getType()+","+a[j];
            	if(!org.contains(entity)){
                org.add(entity);
            	}

            }
            return org;
		
	}*/
	private LinkedList<String> getOrganization(String []token) {
        Span sp[] =this.organiziationModel.find(token);
        String a[] = Span.spansToStrings(sp, token);
        LinkedList<String> org=new LinkedList<String>();
        org.add("ORGANIZATION");
        for (int j = 0; j < a.length; j++) {
        	String entity=a[j];
        	if(!org.contains(entity)){
            org.add(entity);
        	}

        }
        return org;
	
}
/*
	private LinkedList<String> getLocation(String []token) {
        Span sp[] =this.locationModel.find(token) ;
        String a[] = Span.spansToStrings(sp, token);
        LinkedList<String> location=new LinkedList<String>();
        for (int j = 0; j < a.length; j++) {
        	String entity=sp[j].getType()+","+a[j];
        	if(!location.contains(entity))
            location.add(entity);
        	}

	return location;
	}

	//modifica questa in modo tale che appena crei lista fai add("person") e poi nel for aggiungi solo il nome
	//delle entità
	*/
	private LinkedList<String> getLocation(String []token) {
        Span sp[] =this.locationModel.find(token) ;
        String a[] = Span.spansToStrings(sp, token);
        LinkedList<String> location=new LinkedList<String>();
        location.add("LOCATION");
        for (int j = 0; j < a.length; j++) {
        	String entity=a[j];
        	if(!location.contains(entity))
            location.add(entity);
        	}

	return location;
	}
	/*private LinkedList<String> getPersonName(String [] token) {
            Span sp[] =this.personModel.find(token) ;
            String a[] = Span.spansToStrings(sp, token);
            LinkedList<String> personName=new LinkedList<String>();
            for (int j = 0; j < a.length; j++) {
            	String entity=sp[j].getType()+","+a[j];
            	if(!personName.contains(entity))
                personName.add(entity);
            	}

		return personName;
	}*/
	private LinkedList<String> getPersonName(String [] token) {
        Span sp[] =this.personModel.find(token) ;
        String a[] = Span.spansToStrings(sp, token);
        LinkedList<String> personName=new LinkedList<String>();
        personName.add("PERSON");
        for (int j = 0; j < a.length; j++) {
        	String entity=a[j];
        	if(!personName.contains(entity))
            personName.add(entity);
        	}

	return personName;
}
	
	
	public synchronized LinkedList<String> getEntities(String html) {
		LinkedList<String> entities=new LinkedList<String>();
		String []token =this.tokenizer(html);
		entities.addAll(this.getOrganization(token));
		entities.addAll(this.getPersonName(token));
		entities.addAll(this.getLocation(token));
		
		return entities;
	}
}
