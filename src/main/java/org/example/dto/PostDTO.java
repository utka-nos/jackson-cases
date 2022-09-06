package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.example.dto.views.JsonViews;
import org.example.jsonprops.AuthorDeserializer;
import org.example.jsonprops.AuthorSerializer;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    @JsonView({JsonViews.Full.class, JsonViews.FullPost.class})
    private Long id;

    @JsonView({JsonViews.Main.class, JsonViews.FullPost.class})
    private String content;

    @JsonView({JsonViews.Full.class, JsonViews.FullPost.class})
    //под этим названием ключа будет скрываться это поле при серриализации и десерриализации
    @JsonProperty("author_id")
    // устанавливаем алгоритм серриализации и десериализации
    @JsonDeserialize(using = AuthorDeserializer.class)
    @JsonSerialize(using = AuthorSerializer.class)
    private UserDTO author;

    @JsonView({JsonViews.Main.class, JsonViews.FullPost.class})
    private LocalDateTime creationTime;

}
