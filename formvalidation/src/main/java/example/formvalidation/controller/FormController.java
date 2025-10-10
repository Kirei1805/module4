package example.formvalidation.controller;


import example.formvalidation.model.User;
import example.formvalidation.dto.UserDto;
import example.formvalidation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import example.formvalidation.validation.UserValidator;

@Controller
public class FormController {

    @Autowired
    private UserService userService;

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator());
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "index";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());

        userService.save(user);
        model.addAttribute("user", user);
        return "result";
    }
}

