package com.example.blogAPI.controllers;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.items.User;
import com.example.blogAPI.services.BlogPostService;
import com.example.blogAPI.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog-post")
public class BlogPostController {
    private BlogPostService blogPostService;
    private UserService userService;
    public BlogPostController(BlogPostService blogPostService, UserService userService) {
        this.blogPostService = blogPostService;
        this.userService = userService;
    }

    // Get mapping to get a BlogPost object by blogPostId.
    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<BlogPost> getOneBlogPostById(int id) {
        // Get BlogPost object by id with getBlogPostById.
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if(blogPost != null) {
            // Get request succeeded.
            return ResponseEntity.status(HttpStatus.OK).body(blogPost);
        }
        // Get request didn't find BlogPost object with given id.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Get mapping to get a list of BlogPost objects.
    @CrossOrigin
    @GetMapping("/get-all-posts")
    public List<BlogPost> getAllBlogPosts() {
       return blogPostService.getAllPosts();
    }

    // Get mapping to get a list of BlogPost objects by page.
    @CrossOrigin
    @GetMapping("/get-all-posts-by-page")
    public List<BlogPost> getAllBlogPostsByPage(String sortBy, int page) {
        return blogPostService.getAllPostsWithPage(sortBy, page);
    }

    // Get mapping to get the amount of posts in the database.
    @CrossOrigin
    @GetMapping("/get-number-of-posts")
    public int getNUmberOfPosts() {
        return blogPostService.getNumberOfPostsInDB();
    }

    // Post mapping to create a new BlogPost object.
    @CrossOrigin
    @PostMapping("/create-post")
    public ResponseEntity<String> createNewBlogPost(String title, String textContent, String date, int userId) {
        // Find user by userId
        User user = userService.getUserByUserId(userId);
        if(user != null) {
            // Create a BlogPost object with given values.
            BlogPost blogPost = new BlogPost(title, textContent, date, user);
            // Check if addBlogPost successes or not.
            boolean success = blogPostService.addBlogPost(blogPost);
            if(success) {
                // Post request succeeded.
                return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully");
            }
            // Post request failed.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add post");
        }
        // Couldn't find user with given userId.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to find user");
    }

    // Put mapping to edit a BlogPost object.
    @CrossOrigin
    @PutMapping("/edit-post")
    public ResponseEntity<String> editBlogPost(int id, String title, String textContent, String date, int userId) {
        // Find user by userId
        User user = userService.getUserByUserId(userId);
        if(user != null) {
            // Create a BlogPost object with given values.
            BlogPost blogPost = new BlogPost(id, title, textContent, date, user);
            // Check if updateBlogPost successes or not.
            boolean success = blogPostService.updateBlogPost(blogPost);
            if(success) {
                // Put request succeeded.
                return ResponseEntity.status(HttpStatus.OK).body("Edited successfully");
            }
            else {
                // Put request failed.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to edit post");
            }
        }
        // Couldn't find user with given userId.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to find user");
    }

    // Delete mapping to remove a BlogPost object.
    @CrossOrigin
    @DeleteMapping("/delete-post")
    public ResponseEntity<String> deleteBlogPostById(int id) {
        // Check if deleteBlogPostById successes or not.
        boolean success = blogPostService.deleteBlogPostById(id);
        if (success) {
            // Delete request succeeded.
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully") ;
        }
        // Delete request failed.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to deleted post");
    }


}
