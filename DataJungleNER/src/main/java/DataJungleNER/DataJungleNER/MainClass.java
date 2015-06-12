package DataJungleNER.DataJungleNER;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class MainClass {
	public static void main(String [] args){
		PropertiesManager p=new PropertiesManager();
	//NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
	//e.exec();
		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");
		//e.exec();
		
		
		WrapperStanfordNLP w=new WrapperStanfordNLP();
		String s=w.getEntitiesss("We'll be  stuck waiting for the end of the year to see if this happens since California sees most of its annual rainfall during the winter months.In the meantime, many in California are hoping and praying this El Niño event remains strong through next winter, and if forecasts are any indication, there's an 85 percent chance those hopes and prayers will get answered.");
		System.out.println(s);
		
		
		
		
		
		String[] d=Jsoup.parse(s).getElementsByTag("ORGANIZATION").html().split("/n");
		LinkedList<String> result = new LinkedList<String>();
		result.addAll(Arrays.asList(d));
		for(String z:result){
			System.out.println(z.equals(" "));
		}
		
		 
	}

}
