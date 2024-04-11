package com.example.blogAPI.services;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.items.User;
import com.example.blogAPI.repositories.BlogPostRepository;
import com.example.blogAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlogPostService {

    private BlogPostRepository repo;
    public BlogPostService(BlogPostRepository repo) {
        this.repo = repo;
    }

    // Get all BlogPost to a list from database.
    public ArrayList<BlogPost> getAllPosts() {
        return (ArrayList<BlogPost>) repo.findAll();
    }

    // Get all BlogPosts sorted by user id with bubble sort from database.
    public ArrayList<BlogPost> getAllBlogPostsSortedByUserId() {
        ArrayList<BlogPost> allPosts = (ArrayList<BlogPost>) repo.findAll();
        for (int i = 0; i < allPosts.size(); i++) {
            for (var j = 0; j < (allPosts.size() - i - 1); j++) {
                if (allPosts.get(j).getUserId() > allPosts.get(j + 1).getUserId()) {
                    BlogPost temp = allPosts.get(j);
                    allPosts.set(j, allPosts.get(j + 1));
                    allPosts.set(j + 1, temp);
                }
            }
        }
        return allPosts;
    }

    // Get BlogPost by id from database.
    public BlogPost getBlogPostById(int id) {
        if(repo.existsById(id)) {
            return repo.findById(id).get();
        }
        return null;
    }

    // Add BlogPost to database.
    public boolean addBlogPost(BlogPost blogPost) {
        BlogPost newBlogPost = repo.save(blogPost);
        blogPost.setId(newBlogPost.getId());
        return true;
    }

    // Update BlogPost to database.
    public boolean updateBlogPost(BlogPost blogPost) {
        if(repo.existsById(blogPost.getId())){
            repo.save(blogPost);
            return true;
        }
        return false;
    }

    // Delete BlogPost from database.
    public boolean deleteBlogPostById(int id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
