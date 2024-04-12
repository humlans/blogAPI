package com.example.blogAPI.repositories;

import com.example.blogAPI.items.BlogPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    // Sends a Query to MySQL to sort by owner_id.
    @Query(value = "SELECT * FROM blog_post_table ORDER BY owner_id;", nativeQuery = true)
    List<BlogPost> findByOwnerId(Pageable pageable);


    // Sends a Query to MySQL to sort by date with the newest first.
    @Query(value = "SELECT * FROM blog_post_table ORDER BY date DESC;", nativeQuery = true)
    List<BlogPost> findByDateNewest(Pageable pageable);


    // Sends a Query to MySQL to sort by date with the oldest first.
    @Query(value = "SELECT * FROM blog_post_table ORDER BY date;", nativeQuery = true)
    List<BlogPost> findByDateOldest(Pageable pageable);

}
