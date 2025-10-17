package example.blog3.controller;
import example.blog3.model.Blog;
import example.blog3.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("posts", blogService.findFirst20());
        return "blog_list";
    }

    @GetMapping("/search")
    public String searchBlogs(@RequestParam("keyword") String keyword, Model model) {
        List<Blog> posts = blogService.searchByTitle(keyword);
        model.addAttribute("posts", posts);
        return "blog_list_ajax"; // render lại phần danh sách
    }

    @GetMapping("/load-more")
    public String loadMore(@RequestParam("offset") int offset, Model model) {
        List<Blog> posts = blogService.findNext20(offset);
        model.addAttribute("posts", posts);
        return "blog_list_ajax"; // chỉ trả về HTML nhỏ
    }
}


