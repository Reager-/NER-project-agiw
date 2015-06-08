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
    	
    	
    	
    	
    
	public LinkedList<String> getOrganization(String html) {
		InputStream is;
        TokenNameFinderModel model;
        NameFinderME nameFinder;
        try {
            is = new FileInputStream("traning/en-ner-organization.bin");
            model = new TokenNameFinderModel(is);
            nameFinder = new NameFinderME(model);
            String []token =this.tokenizer(html);
            Span sp[] = nameFinder.find(token);
            String a[] = Span.spansToStrings(sp, token);
            LinkedList<String> org=new LinkedList<String>();
            for (int j = 0; j < a.length; j++) {
                org.add(sp[j].getType()+","+a[j]+","+sp[j].getStart());
                

            }

           
          return org;
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
		return null;
	}

	public String[] getLocation(String html) {
		
		return null;
	}

	public String[] getPersonName(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<String> getEntities(String htlm) {
		// TODO Auto-generated method stub
		return null;
	}

}
