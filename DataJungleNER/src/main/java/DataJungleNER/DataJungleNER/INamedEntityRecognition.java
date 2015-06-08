package DataJungleNER.DataJungleNER;

import java.util.LinkedList;

public interface INamedEntityRecognition {
public LinkedList<String> getEntities(String htlm);
}
