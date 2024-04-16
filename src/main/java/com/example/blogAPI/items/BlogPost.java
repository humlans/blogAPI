package com.example.blogAPI.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "blog_post_table")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition="VARCHAR(100)")
    private String title;
    @Column(columnDefinition="TEXT")
    private String textContent;
    @Column(columnDefinition="DATE")
    private String date;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public BlogPost (String title, String textContent, String date, User user) {
        this.title = title;
        this.textContent = textContent;
        this.date = date;
        this.user = user;
    }

}
