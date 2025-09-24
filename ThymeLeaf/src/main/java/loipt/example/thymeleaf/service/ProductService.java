package loipt.example.thymeleaf.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import loipt.example.thymeleaf.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product existing = entityManager.find(Product.class, id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setManufacturer(product.getManufacturer());
            entityManager.merge(existing);
        }
    }

    @Override
    public void delete(Long id) {
        Product existing = entityManager.find(Product.class, id);
        if (existing != null) {
            entityManager.remove(existing);
        }
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return entityManager.createQuery(
                        "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:kw)", Product.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }
}
