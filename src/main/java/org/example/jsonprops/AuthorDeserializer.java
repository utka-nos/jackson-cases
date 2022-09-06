package org.example.jsonprops;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.example.dto.UserDTO;

import java.io.IOException;

public class AuthorDeserializer extends JsonDeserializer<UserDTO> {
    @Override
    public UserDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long author_id = jsonParser.getValueAsLong();
        UserDTO user = new UserDTO();
        user.setId(author_id);
        return user;
    }
}
