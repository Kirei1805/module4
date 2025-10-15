package example.cart.controller;

import example.cart.model.Cart;
import example.cart.model.Product;
import example.cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView mav = new ModelAndView("shop");
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            return "error";
        }
        model.addAttribute("product", product.get());
        return "detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/shop";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute("cart") Cart cart,
                            @RequestParam(value = "action", required = false) String action) {

        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            cart.addProduct(product.get());
        } else {
            return "error";
        }

        if ("show".equals(action)) {
            return "redirect:/shopping-cart";
        }
        return "redirect:/products/shop";
    }
}
