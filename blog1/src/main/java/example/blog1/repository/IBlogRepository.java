package example.blog1.repository;

import example.blog1.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByAuthorContaining(String name);
    Page<Blog> findAllByAuthorContaining(String name, Pageable pageable);
    
    // Các phương thức bổ sung
    @org.springframework.data.jpa.repository.Query("SELECT b FROM Blog b LEFT JOIN FETCH b.category ORDER BY b.createdAt DESC")
    Page<Blog> findAllWithCategory(Pageable pageable);
    
    @org.springframework.data.jpa.repository.Query("SELECT b FROM Blog b LEFT JOIN FETCH b.category WHERE b.category.id = :categoryId ORDER BY b.createdAt DESC")
    Page<Blog> findByCategoryId(@org.springframework.data.repository.query.Param("categoryId") Long categoryId, Pageable pageable);
    
    @org.springframework.data.jpa.repository.Query("SELECT b FROM Blog b LEFT JOIN FETCH b.category WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY b.createdAt DESC")
    Page<Blog> searchBlogs(@org.springframework.data.repository.query.Param("keyword") String keyword, Pageable pageable);
    
    @org.springframework.data.jpa.repository.Query("SELECT b FROM Blog b LEFT JOIN FETCH b.category WHERE b.id = :id")
    Optional<Blog> findByIdWithCategory(@org.springframework.data.repository.query.Param("id") Long id);
    
    @org.springframework.data.jpa.repository.Query("SELECT b FROM Blog b LEFT JOIN FETCH b.category ORDER BY b.createdAt DESC")
    List<Blog> findTop5ByOrderByCreatedAtDesc();
}
