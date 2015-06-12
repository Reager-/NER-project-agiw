package DataJungleNER.DataJungleNER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private Properties properties;
public PropertiesManager(){
	this.properties=new Properties();
	try {
		properties.load(new FileInputStream("conf.properties"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public String getValueForKey(String key){
	return this.properties.getProperty(key);
	
}
}
