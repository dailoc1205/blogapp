package com.vti.blogapp.controller;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentFilterForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.mapper.CommentMapper;
import com.vti.blogapp.service.CommentService;
import com.vti.blogapp.validation.CommentIdExixts;
import com.vti.blogapp.validation.PostIdExixts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(CommentFilterForm form,Pageable pageable){

        return commentService.findAll(form,pageable);
    }
    @GetMapping("/api/v1/posts/{postId}/comments")
    public Page<CommentDto> findByPostId (@PathVariable ("postId")@CommentIdExixts Long postId, Pageable pageable){
        return commentService.findByPostId(postId, pageable);
    }
    @PostMapping ("/api/v1/posts/{postId}/comments")
    public CommentDto create(@RequestBody @Valid CommentCreateForm form,
                             @PathVariable("postId") @CommentIdExixts Long postId){
        return commentService.create(form, postId);
    }
    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@RequestBody @Valid CommentUpdateForm form,
                             @PathVariable("id") @PostIdExixts Long id){
        return commentService.update(form,id);
    }
    @DeleteMapping("/api/v1/comments/{id}")
    public void  deleteById(@PathVariable("id") @CommentIdExixts Long id){
        commentService.deleteById(id);
    }
    @DeleteMapping("/api/v1/comments/email/{email}")
    public  void deleteByEmail(@PathVariable("email") String email){
        commentService.deleteByEmail(email);
    }

}
