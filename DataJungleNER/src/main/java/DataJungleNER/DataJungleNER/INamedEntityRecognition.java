package DataJungleNER.DataJungleNER;

public interface INamedEntityRecognition {
public String[] getOrganization(String html);
public String[] getLocation(String html);
public String[] getPersonName(String html);
}
