package DataJungleNER.DataJungleNER;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class MainClass {
	public static void main(String [] args){
		PropertiesManager p=new PropertiesManager();
	NERexecutor e=new NERexecutor(p.getValueForKey("urls"),p.getValueForKey("outputEnt"));
	e.exec();
		//NERexecutorSeriale e=new NERexecutorSeriale("target/prova.txt","target/entitàSeriale.txt");
		//e.exec();
		
	
		
	}
	


}
