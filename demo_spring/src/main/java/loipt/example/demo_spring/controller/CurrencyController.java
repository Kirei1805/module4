package loipt.example.demo_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyController {

    @GetMapping("")
    public String showForm() {
        return "currency";
    }

    @GetMapping("/convert")
    public String convert(
            @RequestParam("amount") double amount,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            Model model) {

        double rate = 1.0;
        if (from.equals("USD") && to.equals("VND")) rate = 25000;
        else if (from.equals("VND") && to.equals("USD")) rate = 1.0 / 25000;
        else if (from.equals("USD") && to.equals("EUR")) rate = 0.9;
        else if (from.equals("EUR") && to.equals("USD")) rate = 1.1;
        else if (from.equals("VND") && to.equals("EUR")) rate = 1.0 / 27000;
        else if (from.equals("EUR") && to.equals("VND")) rate = 27000;

        double result = amount * rate;

        model.addAttribute("amount", amount);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("result", result);

        return "result";
    }
}

