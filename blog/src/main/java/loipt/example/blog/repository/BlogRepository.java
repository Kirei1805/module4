package loipt.example.blog.repository;

import loipt.example.blog.entity.Blog;

import java.util.List;

public interface BlogRepository {
    Blog save(Blog blog);
    Blog findById(Long id);
    List<Blog> findAll();
    Blog update(Blog blog);
    void deleteById(Long id);
}


