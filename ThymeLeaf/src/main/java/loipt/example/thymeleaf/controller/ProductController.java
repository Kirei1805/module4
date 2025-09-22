package loipt.example.thymeleaf.controller;
import loipt.example.thymeleaf.model.Product;
import loipt.example.thymeleaf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/view";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String keyword, Model model) {
        model.addAttribute("products", productService.searchByName(keyword));
        return "product/list";
    }
}

