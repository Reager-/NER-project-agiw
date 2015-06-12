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
public INamedEntityRecognition getNLPlibrary(){
	INamedEntityRecognition o=null;
	try {
		o = (INamedEntityRecognition) Class.forName("DataJungleNER.DataJungleNER.Wrapper"+new PropertiesManager().getValueForKey("libraryToUse")).newInstance();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("nome libreria errata scegli tra: OpenNLP , AlchemyAPI , StanfordNLP");
	} catch (InstantiationException e) {	
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}
	return   o;
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
