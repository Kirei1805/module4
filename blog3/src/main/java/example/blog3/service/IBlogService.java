package example.blog3.service;

import example.blog3.model.Blog;
import java.util.List;


public interface IBlogService {
    List<Blog> findFirst20();
    List<Blog> findNext20(int offset);
    List<Blog> searchByTitle(String keyword);
}
