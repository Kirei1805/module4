package loipt.example.blog.controller;

import loipt.example.blog.entity.Blog;
import loipt.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("blogs", blogService.getAll());
        return "blog/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/form";
    }

    @PostMapping
    public String create(@ModelAttribute("blog") Blog blog) {
        blogService.create(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/blogs";
        model.addAttribute("blog", blog);
        return "blog/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) return "redirect:/blogs";
        model.addAttribute("blog", blog);
        return "blog/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("blog") Blog blog) {
        blog.setId(id);
        blogService.update(blog);
        return "redirect:/blogs/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }
}


