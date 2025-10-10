package example.songvalidation.controller;

import example.songvalidation.dto.SongDTO;
import example.songvalidation.model.Song;
import example.songvalidation.service.SongService;
import example.songvalidation.validate.SongValidator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private SongValidator songValidator;

    @InitBinder("songDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(songValidator);
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "songs/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("songDTO", new SongDTO());
        return "songs/form";
    }

    @PostMapping("/add")
    public String addSong(@Valid @ModelAttribute("songDTO") SongDTO songDTO,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "songs/form";
        }
        Song song = new Song();
        BeanUtils.copyProperties(songDTO, song);
        songService.save(song);
        model.addAttribute("song", song);
        return "songs/result";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Song song = songService.findById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        SongDTO dto = new SongDTO();
        BeanUtils.copyProperties(song, dto);
        model.addAttribute("songDTO", dto);
        model.addAttribute("songId", id);
        return "songs/form";
    }

    @PostMapping("/edit/{id}")
    public String editSong(@PathVariable("id") Long id,
                           @Valid @ModelAttribute("songDTO") SongDTO songDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("songId", id);
            return "songs/form";
        }
        Song song = songService.findById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        BeanUtils.copyProperties(songDTO, song);
        songService.save(song);
        model.addAttribute("song", song);
        return "songs/result";
    }
}
