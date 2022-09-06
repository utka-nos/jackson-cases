package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PostDTO;
import org.example.dto.views.JsonViews;
import org.example.model.Post;
import org.example.repo.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
@Slf4j
public class PostController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @PostMapping
    @JsonView(JsonViews.Main.class)
    public PostDTO addNewPost(
            @RequestBody PostDTO postDTO
    ) {
        Post postToSave = modelMapper.map(postDTO, Post.class);
        Post savedPost = postRepo.save(postToSave);
        log.info("post saved : {{}}", savedPost);

        return modelMapper.map(savedPost, PostDTO.class);
    }

    @JsonView(JsonViews.FullPost.class)
    @GetMapping("/{id}")
    public PostDTO getFullPost(
            @PathVariable Long id
    ) {
        Post post = postRepo.findById(id).get();
        log.info("Post to get : {{}}", post);

        return modelMapper.map(post, PostDTO.class);
    }

}
