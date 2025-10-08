package example.blog1.service;

import example.blog1.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> findAllByAuthorContaining(String name);
    List<Blog> findAll();
    Page<Blog> findAll(String name, Pageable pageable);
    boolean save(Blog blog);
    void delete(Blog blog);
    boolean update(Blog blog);
    Blog findById(int id);
    
    Page<Blog> findAllWithCategory(Pageable pageable);
    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Blog> searchBlogs(String keyword, Pageable pageable);
    List<Blog> findTop5Recent();
}
