package DataJungleNER.DataJungleNER;

public class NERFactory {
	private static NERFactory istanza; 
private NERFactory(){
	
}
public static NERFactory getIstance(){

	if(istanza==null)
		istanza=new NERFactory();
 return istanza;
}
public INamedEntityRecognition getOpenNLP(){
	INamedEntityRecognition o=new WrapperOpenNLP();
	return o;
	}
public INamedEntityRecognition getAlchemyAPI(){
	INamedEntityRecognition o=new WrapperAlchemyAPI();
	return o;
	}
public INamedEntityRecognition getStanfordNLP(){
	INamedEntityRecognition o=new WrapperStanfordNLP();
	return o;
	}
}
