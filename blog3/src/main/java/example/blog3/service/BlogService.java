package example.blog3.service;


import example.blog3.model.Blog;
import example.blog3.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findFirst20() {
        Pageable pageable = PageRequest.of(0, 20);
        return blogRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public List<Blog> findNext20(int offset) {
        Pageable pageable = PageRequest.of(offset / 20, 20);
        return blogRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public List<Blog> searchByTitle(String keyword) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
