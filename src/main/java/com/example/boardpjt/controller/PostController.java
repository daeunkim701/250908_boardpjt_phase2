package com.example.boardpjt.controller;

import com.example.boardpjt.model.dto.PostDTO;
import com.example.boardpjt.service.PostService;
import com.example.boardpjt.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // 스캔
@RequiredArgsConstructor // 의존성
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    // 게시물 목록
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts",
                postService.findAll()
                        .stream().map(p -> new PostDTO.Response(
                                p.getId(),
                                p.getTitle(),
                                p.getContent(),
                                p.getAuthor().getUsername(),
                                p.getCreatedAt().toString()
                        )));
        return "post/list"; // templates/post/list.html
    }
    // 개별 게시물
    @GetMapping("/{id}")
    public String list(
            @PathVariable Long id,
            Model model) {
        // 각각 개별이니까... 1개.
        model.addAttribute("post", postService.findById(id));
        return "post/detail"; // templates/post/list.html
    }
    // 게시물 작성
    @GetMapping("/new")
    public String createForm(
            Model model, Authentication authentication) {
        PostDTO.Request dto = new PostDTO.Request();
        dto.setUsername(authentication.getName());
        model.addAttribute("post", dto);
        return "post/form"; // templates/post/list.html
    }
    // POST 처리...
    @PostMapping("/new")
    public String create(@ModelAttribute PostDTO.Request dto, Authentication authentication) {
        // dto? -> username
        // 불일치할 때 에러를?
        dto.setUsername(authentication.getName());
        postService.createPost(dto);
        return "redirect:/posts";
    }
}