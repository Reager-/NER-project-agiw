package DataJungleNER.DataJungleNER;

import java.util.LinkedList;

public interface INamedEntityRecognition {
public LinkedList<String> getOrganization(String html);
public String[] getLocation(String html);
public String[] getPersonName(String html);
}
