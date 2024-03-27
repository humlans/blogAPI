package com.example.blogAPI.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "blog_post_table")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String textContent;
    @Column
    private String date;
    @Column
    private int userId;

    public BlogPost (String title, String textContent, String date, int userId) {
        this.title = title;
        this.textContent = textContent;
        this.date = date;
        this.userId = userId;
    }

}
