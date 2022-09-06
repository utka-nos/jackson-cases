package org.example.jsonprops;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.dto.PostDTO;

import java.io.IOException;
import java.util.List;

public class PostSerializer extends JsonSerializer<List<PostDTO>> {
    @Override
    public void serialize(List<PostDTO> postDTOS, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Если нет родительского корня в текущей json строке или если представлен родитель,
        // но у него имя ключа не author, то спокойно записываем,
        // иначе ничего не записываем - будет просто пустое значение
        if (jsonGenerator.getOutputContext().getParent() == null
                || !"author".equals(jsonGenerator.getOutputContext().getParent().getCurrentName())) {
            jsonGenerator.writeObject(postDTOS);
        }
    }
}
