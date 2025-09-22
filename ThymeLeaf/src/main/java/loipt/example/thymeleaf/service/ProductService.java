package loipt.example.thymeleaf.service;

import loipt.example.thymeleaf.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1L, "Laptop Dell", 1500, "Laptop văn phòng", "Dell"));
        products.add(new Product(2L, "iPhone 15", 1200, "Điện thoại cao cấp", "Apple"));
        products.add(new Product(3L, "Samsung TV", 800, "TV 4K UHD", "Samsung"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        product.setId((long) (products.size() + 1));
        products.add(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product existing = findById(id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setManufacturer(product.getManufacturer());
        }
    }

    @Override
    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
