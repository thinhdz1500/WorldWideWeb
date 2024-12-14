package com.thinne.frontend.controllers;

import com.thinne.backend.models.Post;
import com.thinne.backend.models.User;
import com.thinne.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @RequestMapping("")
    public ResponseEntity<List<Post>> getPost() {
        return ResponseEntity.ok(postRepository.findAll());
    }
}
