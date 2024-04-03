package com.example.blogAPI.controllers;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.services.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog-post")
public class BlogPostController {
    private BlogPostService blogPostService;
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<BlogPost> getOneBlogPostById(int id) {
        BlogPost blogPost = blogPostService.getBlosPostById(id);
        if(blogPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body(blogPost);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @CrossOrigin
    @GetMapping("/get-all-posts")
    public ArrayList<BlogPost> getAllBlogPosts() {
       return (ArrayList<BlogPost>) blogPostService.getAllPosts();
    }
    @CrossOrigin
    @PostMapping("/create-post")
    public ResponseEntity<String> createNewBlogPost(String title, String textContent, String date, int userId) {
        BlogPost blogPost = new BlogPost(title, textContent, date, userId);
        boolean success = blogPostService.addBlogPost(blogPost);
        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add post");
    }

    //ÄNDRAT I EDIT POST
    @CrossOrigin
    @PutMapping("/edit-post")
    public ResponseEntity<String> editBlogPost(int id, String title, String textContent, String date, int userId) {
        BlogPost blogPost = new BlogPost(id, title, textContent, date, userId);
        boolean success = blogPostService.updateBlogPost(blogPost);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).body("Edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to edit post");
        }
    }


    @CrossOrigin
    @DeleteMapping("delete-post")
    public ResponseEntity<String> deleteBlogPostById(int id) {
        boolean success = blogPostService.deleteBlogPostById(id);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully") ;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to deleted post");
    }


}
