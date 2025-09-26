package loipt.example.blog.service;

import loipt.example.blog.entity.Blog;
import loipt.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog update(Blog blog) {
        return blogRepository.update(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}


