package com.example.blogAPI.services;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlogPostService {

    private BlogPostRepository repo;
    public BlogPostService(BlogPostRepository repo) {
        this.repo = repo;
    }

    public ArrayList<BlogPost> getAllPosts() {
        return (ArrayList<BlogPost>) repo.findAll();
    }

    public BlogPost getBlosPostById(int id) {
        if(repo.existsById(id)) {
            return repo.findById(id).get();
        }
        return null;
    }

    public boolean addBlogPost(BlogPost blogPost) {
        BlogPost newBlogPost = repo.save(blogPost);
        blogPost.setId(newBlogPost.getId());
        return true;
    }

    public boolean updateBlogPost(BlogPost blogPost) {
        if(repo.existsById(blogPost.getId())){
            repo.save(blogPost);
            return true;
        }
        return false;
    }

    public boolean deleteBlogPostById(int id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
