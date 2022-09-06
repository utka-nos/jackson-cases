package org.example.jsonprops;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.dto.UserDTO;
import org.example.dto.views.JsonViews;

import java.io.IOException;

public class AuthorSerializer extends JsonSerializer<UserDTO> {
    @Override
    public void serialize(UserDTO user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(user.getId());
        // Если у нас JsonView указан как FullPost,
        // то дополнительно к author_id добавляем информацию об авторе
        if (JsonViews.FullPost.class.equals(serializerProvider.getActiveView())) {
            jsonGenerator.writeObjectField("author", user);
        }
    }
}
