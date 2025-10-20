package example.blog2.service;

import example.blog2.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void delete(Long id);
    Page<Blog> findAllPaginated(Pageable pageable);
    Page<Blog> searchByTitleOrContent(String searchTerm, Pageable pageable);
}
