package loipt.example.homdientu.controller;

import loipt.example.homdientu.model.EmailSettings;
import loipt.example.homdientu.repository.SettingsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsRepository repo;

    public SettingsController(SettingsRepository repo) {
        this.repo = repo;
    }

    @ModelAttribute("languages")
    public List<String> languages() {
        return List.of("English", "Vietnamese", "Japanese", "Chinese");
    }

    @ModelAttribute("pageSizes")
    public List<Integer> pageSizes() {
        return List.of(5, 10, 15, 25, 50, 100);
    }

    @GetMapping
    public String view(Model model) {
        model.addAttribute("settings", repo.getSettings());
        return "view";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("emailSettings", repo.getSettings());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("emailSettings") EmailSettings s) {
        repo.saveSettings(s);
        return "redirect:/settings";
    }
}
