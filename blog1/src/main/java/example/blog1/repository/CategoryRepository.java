package example.blog1.repository;

import example.blog1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Sử dụng các method có sẵn của JpaRepository
}
