package example.book.controller;

import example.book.model.Book;
import example.book.service.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id, Model model) {
        Book book = bookService.findAll()
                .stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("book", book);
        return "detail";
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        String code = bookService.borrowBook(id);
        model.addAttribute("message", "Mượn thành công! Mã mượn sách của bạn là: " + code);
        return "success";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String code, Model model) {
        bookService.returnBook(code);
        model.addAttribute("message", "Trả sách thành công!");
        return "success";
    }
}
