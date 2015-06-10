package DataJungleNER.DataJungleNER;


import java.io.StringWriter;
import java.util.LinkedList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alchemyapi.api.AlchemyAPI;

public class WrapperAlchemyAPI implements INamedEntityRecognition {
	
	private AlchemyAPI alchemyObj;
	private LinkedList<String> locations = new LinkedList<String>();
	private LinkedList<String> organizations = new LinkedList<String>();

	public WrapperAlchemyAPI(){
		try {
			setAlchemyObj(AlchemyAPI.GetInstanceFromFile("AlchemyAPI/api_key.txt"));
			locations.add("City");
			locations.add("Continent");
			locations.add("Country");
			locations.add("Region");
			locations.add("StateOrCounty");
			organizations.add("Company");
			organizations.add("Organization");
			organizations.add("RadioStation");
			organizations.add("TelevisionStation");
			
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
	
	public LinkedList<String> getLocations() {
		return locations;
	}

	public void setLocations(LinkedList<String> locations) {
		this.locations = locations;
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
			result = formatOutput(doc);
			// System.out.println(getStringFromDocument(doc)); print XML document
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
        return null;
	}

	private LinkedList<String> formatOutput(Document doc) {
		NodeList entities = doc.getElementsByTagName("entity");
		LinkedList<String> result = new LinkedList<String>();
		for (int temp = 0; temp < entities.getLength(); temp++) {
			 
			Node nNode = entities.item(temp);
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				if (isAnOrganization(eElement.getElementsByTagName("type").item(0).getTextContent())){
				result.add("organization,"+ eElement.getElementsByTagName("text").item(0).getTextContent());
				}
				if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("Person")){
					result.add("person,"+ eElement.getElementsByTagName("text").item(0).getTextContent());
				}
				if (isALocation(eElement.getElementsByTagName("type").item(0).getTextContent())){
					result.add("location,"+ eElement.getElementsByTagName("text").item(0).getTextContent());
				}
			}
		}
		return result;
	}

	private boolean isALocation(String textContent){
		return this.locations.contains(textContent);
	}
	
	private boolean isAnOrganization(String textContent){
		return this.organizations.contains(textContent);
	}

}
