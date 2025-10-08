package example.blog1.controller;

import example.blog1.model.Blog;
import example.blog1.model.Category;
import example.blog1.service.BlogService;
import example.blog1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    
    private final BlogService blogService;
    private final CategoryService categoryService;
    
    @GetMapping("/list")
    public String listBlogs(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String keyword) {
        
        Page<Blog> blogs;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            blogs = blogService.searchBlogs(keyword.trim(), page, size, "createdAt", "desc");
            model.addAttribute("keyword", keyword);
        } else {
            blogs = blogService.findAll(page, size, "createdAt", "desc");
        }
        
        model.addAttribute("blogs", blogs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogs.getTotalPages());
        model.addAttribute("totalItems", blogs.getTotalElements());
        
        return "blog/list";
    }
    
    @GetMapping("/add")
    public String addBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/add";
    }
    
    @PostMapping("/add")
    public String addBlog(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        if (blogService.save(blog)) {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm blog thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm blog!");
        }
        return "redirect:/blog/list";
    }
    
    @GetMapping("/detail/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id.intValue());
        if (blog == null) {
            return "redirect:/blog/list";
        }
        model.addAttribute("blog", blog);
        return "blog/detail";
    }
    
    @GetMapping("/edit/{id}")
    public String editBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id.intValue());
        if (blog == null) {
            return "redirect:/blog/list";
        }
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.findAll());
        return "blog/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        Blog existingBlog = blogService.findById(id.intValue());
        if (existingBlog == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy blog!");
            return "redirect:/blog/list";
        }
        
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        existingBlog.setAuthor(blog.getAuthor());
        existingBlog.setCategory(blog.getCategory());
        
        if (blogService.update(existingBlog)) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật blog thành công!");
            return "redirect:/blog/detail/" + id;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật blog!");
            return "redirect:/blog/edit/" + id;
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id.intValue());
        if (blog != null) {
            blogService.delete(blog);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa blog thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy blog!");
        }
        return "redirect:/blog/list";
    }
}