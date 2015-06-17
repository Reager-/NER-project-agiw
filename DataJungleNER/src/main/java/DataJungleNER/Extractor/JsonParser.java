package DataJungleNER.Extractor;

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

}
