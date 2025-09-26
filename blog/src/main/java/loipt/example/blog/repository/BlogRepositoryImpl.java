package loipt.example.blog.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import loipt.example.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BlogRepositoryImpl implements BlogRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Blog save(Blog blog) {
        entityManager.persist(blog);
        return blog;
    }

    @Override
    public Blog findById(Long id) {
        return entityManager.find(Blog.class, id);
    }

    @Override
    public List<Blog> findAll() {
        return entityManager.createQuery("SELECT b FROM Blog b ORDER BY b.createdAt DESC", Blog.class)
                .getResultList();
    }

    @Override
    public Blog update(Blog blog) {
        return entityManager.merge(blog);
    }

    @Override
    public void deleteById(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            entityManager.remove(blog);
        }
    }
}


