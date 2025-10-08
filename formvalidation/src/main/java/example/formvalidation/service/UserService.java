package example.formvalidation.service;

import example.formvalidation.model.User;
import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
}
