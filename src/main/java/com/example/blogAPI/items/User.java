package com.example.blogAPI.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "user_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "posts"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String userName;
    @Column
    private String password;
    //@OneToMany(targetEntity = BlogPost.class, cascade = CascadeType.ALL, mappedBy = "user")
    //@JoinColumn(name = "id_of_user", referencedColumnName = "id")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<BlogPost> posts;

    public void addPost(BlogPost blogPost){
        posts.add(blogPost);
    }
}
