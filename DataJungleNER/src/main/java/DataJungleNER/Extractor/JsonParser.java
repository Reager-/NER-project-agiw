package DataJungleNER.Extractor;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class JsonParser {
	//da una stringa json estrae il campo url
   public static String takeUrlfromJson(String objectJson){
	   Object obj;
	   String url="";
	   JSONParser parser = new JSONParser();
	   try {
		obj = parser.parse(objectJson);
		JSONObject jsonObject = (JSONObject) obj;
		url = (String) jsonObject.get("url");
		return url;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return url;
		 
   }
   //metodo da non usare lavora sul file json
	public static void takeURLfromJson(String pathfile){
		JSONParser parser = new JSONParser();
		Object obj;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(pathfile));
			String line;
			LinkedList<String> list=new LinkedList<String>();
			while ((line = br.readLine()) != null) {
				obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) obj;
				String name = (String) jsonObject.get("url");
				if(!list.contains(name))
					list.add(name);


			}
				System.out.println(list.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}



	}

}
