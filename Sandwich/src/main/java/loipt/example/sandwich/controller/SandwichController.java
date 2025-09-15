package loipt.example.sandwich.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class SandwichController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment,
                       Model model) {
        model.addAttribute("condiments", condiment);
        return "result";
    }
}
