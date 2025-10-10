package example.songvalidation.controller;

import example.songvalidation.dto.SongDto;
import example.songvalidation.service.SongService;
import example.songvalidation.validate.SongValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;
    
    @Autowired
    private SongValidate songValidate;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("song", new SongDto());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("song") SongDto song, BindingResult result) {
        songValidate.validate(song, result);
        if (result.hasErrors()) {
            return "form";
        }
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SongDto song = songService.findById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        model.addAttribute("song", song);
        return "form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("song") SongDto song, BindingResult result) {
        songValidate.validate(song, result);
        if (result.hasErrors()) {
            return "form";
        }
        songService.update(id, song);
        return "redirect:/songs";
    }
}
