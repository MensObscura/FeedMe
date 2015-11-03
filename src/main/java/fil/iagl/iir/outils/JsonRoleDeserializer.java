package fil.iagl.iir.outils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import fil.iagl.iir.entite.Role;

/**
 * @author RMS
 *
 * Class permettant de définir comment deserializer un json representant l'enum @see fil.iagl.iir.entite.Role
 */
public class JsonRoleDeserializer extends JsonDeserializer<Role> {

  @Override
  public Role deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    return Role.findById((Integer) node.get("id").numberValue());
  }

}
