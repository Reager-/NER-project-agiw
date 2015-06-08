package DataJungleNER.DataJungleNER;


import java.io.StringWriter;
import java.util.LinkedList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.alchemyapi.api.AlchemyAPI;

public class WrapperAlchemyAPI implements INamedEntityRecognition {
	
	private AlchemyAPI alchemyObj;
	
	public WrapperAlchemyAPI(){
		try {
			setAlchemyObj(AlchemyAPI.GetInstanceFromFile("AlchemyAPI/api_key.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AlchemyAPI getAlchemyObj() {
		return alchemyObj;
	}

	public void setAlchemyObj(AlchemyAPI alchemyObj) {
		this.alchemyObj = alchemyObj;
	}
	
	private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            
            

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

	public LinkedList<String> getEntities(String html) {
		Document doc;
		LinkedList<String> result = new LinkedList<String>();
		try {
			doc = alchemyObj.TextGetRankedNamedEntities(html);
			System.out.println(getStringFromDocument(doc));
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}

}
