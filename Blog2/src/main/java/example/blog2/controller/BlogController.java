package example.blog2.controller;

import example.blog2.dto.BlogDto;
import example.blog2.model.Blog;
import example.blog2.model.Category;
import example.blog2.service.IBlogService;
import example.blog2.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String showAllBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    @PostMapping("/save")
    public String saveBlog(@ModelAttribute("blog") BlogDto blogDto) {
        Category category = categoryService.findById(blogDto.getCategoryId()).orElse(null);
        Blog blog = new Blog(blogDto.getId(), blogDto.getTitle(), blogDto.getContent(), category);
        blogService.save(blog);
        return "redirect:/blogs";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            model.addAttribute("categories", categoryService.findAll());
            return "blog/edit";
        }
        return "error";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

}
