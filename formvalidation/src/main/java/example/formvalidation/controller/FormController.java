package example.formvalidation.controller;

import example.formvalidation.dto.UserDTO;
import example.formvalidation.model.User;
import example.formvalidation.service.UserService;
import example.formvalidation.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder("userDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        userService.save(user);

        model.addAttribute("user", user);
        return "result";
    }
}
