package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import org.example.dto.views.JsonViews;
import org.example.jsonprops.PostSerializer;
import org.example.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    @JsonView(JsonViews.Full.class)
    private Long id;

    @JsonView({JsonViews.Main.class, JsonViews.FullPost.class})
    private String username;

    @JsonView({JsonViews.Full.class, JsonViews.FullPost.class})
    private Set<Role> roles = new HashSet<>();

    @JsonView(JsonViews.Full.class)
    @ToString.Exclude
    @JsonSerialize(using = PostSerializer.class)
    private List<PostDTO> posts = new ArrayList<>();

}
