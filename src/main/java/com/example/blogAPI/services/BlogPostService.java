package com.example.blogAPI.services;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.items.User;
import com.example.blogAPI.repositories.BlogPostRepository;
import com.example.blogAPI.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public int getNumberOfPostsInDB() {
        return repo.findAll().size();
    }

    // Get a sorted list with five blogPost-objects with pageable depending on sortBy-variable.
    public List<BlogPost> getAllPostsWithPage(String sortBy,int page) {

        Pageable pageable = PageRequest.of(page, 5);
        List <BlogPost> blogposts;

        switch (sortBy) {
            case "dateOldest":
                blogposts = repo.findByDateOldest(pageable);
                break;
            case "dateNewest":
                blogposts = repo.findByDateNewest(pageable);
                break;
            case "user":
                blogposts = repo.findByOwnerId(pageable);
                break;
            default:
                blogposts = repo.findAll(pageable).get().toList();
                break;
        }

        return blogposts;
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
