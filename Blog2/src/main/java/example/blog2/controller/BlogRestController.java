package example.blog2.controller;

import example.blog2.dto.BlogDto;
import example.blog2.model.Blog;
import example.blog2.service.IBlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/blogs")
public class BlogRestController {
    private final IBlogService blogService;

    public BlogRestController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<BlogDto> getAllBlogs() {
        return blogService.findAll().stream()
                .map(blog -> new BlogDto(
                        blog.getId(),
                        blog.getTitle(),
                        blog.getContent(),
                        blog.getCategory() != null ? blog.getCategory().getId() : null,
                        blog.getCategory() != null ? blog.getCategory().getName() : "Uncategorized"
                ))
                .toList();
    }


    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.findById(id).orElse(null);
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        blog.setId(id);
        return blogService.save(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.delete(id);
    }
}
