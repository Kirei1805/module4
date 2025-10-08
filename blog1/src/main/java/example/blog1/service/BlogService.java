package example.blog1.service;

import example.blog1.model.Blog;
import example.blog1.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    
    @Autowired
    private IBlogRepository blogRepository;
    
    @Override
    public List<Blog> findAllByAuthorContaining(String name) {
        return blogRepository.findAllByAuthorContaining(name);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAll(String name, Pageable pageable) {
        return blogRepository.findAllByAuthorContaining(name, pageable);
    }

    @Override
    public boolean save(Blog blog) {
        return blogRepository.save(blog) != null;
    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    public boolean update(Blog blog) {
        if (blogRepository.existsById(blog.getId())) {
            blogRepository.save(blog);
            return true;
        }
        return false;
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById((long) id).orElse(null);
    }
    
    @Override
    public Page<Blog> findAllWithCategory(Pageable pageable) {
        return blogRepository.findAllWithCategory(pageable);
    }

    @Override
    public Page<Blog> findByCategoryId(Long categoryId, Pageable pageable) {
        return blogRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Blog> searchBlogs(String keyword, Pageable pageable) {
        return blogRepository.searchBlogs(keyword, pageable);
    }

    @Override
    public List<Blog> findTop5Recent() {
        return blogRepository.findTop5ByOrderByCreatedAtDesc();
    }
    
    // Các phương thức helper cho controller
    public Page<Blog> findAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                   Sort.by(sortBy).descending() : 
                   Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return blogRepository.findAllWithCategory(pageable);
    }
    
    public Page<Blog> findByCategoryId(Long categoryId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                   Sort.by(sortBy).descending() : 
                   Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return blogRepository.findByCategoryId(categoryId, pageable);
    }
    
    public Page<Blog> searchBlogs(String keyword, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                   Sort.by(sortBy).descending() : 
                   Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return blogRepository.searchBlogs(keyword, pageable);
    }
    
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }
}
