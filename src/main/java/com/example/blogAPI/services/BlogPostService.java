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
    private UserRepository userRepo;
    public BlogPostService(BlogPostRepository repo, UserRepository userRepository) {
        this.repo = repo;
        userRepo = userRepository;
    }

    public ArrayList<BlogPost> getAllPosts() {
        return (ArrayList<BlogPost>) repo.findAll();
    }

    public ArrayList<BlogPost> getAllBlogPostsSortedByUserId() {
        ArrayList<BlogPost> allPosts = (ArrayList<BlogPost>) repo.findAll();
        for (int i = 0; i < allPosts.size(); i++) {
            // Last i elements are already in place
            for (var j = 0; j < (allPosts.size() - i - 1); j++) {
                // Checking if the item at present iteration
                // is greater than the next iteration
                if (allPosts.get(j).getUserId() > allPosts.get(j + 1).getUserId()) {
                    // If the condition is true
                    // then swap them
                    BlogPost temp = allPosts.get(j);
                    allPosts.set(j, allPosts.get(j + 1));
                    allPosts.set(j + 1, temp);
                }
            }
        }
        return allPosts;
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
