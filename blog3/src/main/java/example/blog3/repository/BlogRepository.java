package example.blog3.repository;

import example.blog3.model.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<Blog> findByTitleContainingIgnoreCase(String keyword);
}
