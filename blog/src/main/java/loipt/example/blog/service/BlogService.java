package loipt.example.blog.service;

import loipt.example.blog.entity.Blog;

import java.util.List;

public interface BlogService {
    Blog create(Blog blog);
    Blog getById(Long id);
    List<Blog> getAll();
    Blog update(Blog blog);
    void delete(Long id);
}


