package example.book.service;

import example.book.model.Book;
import java.util.List;

public interface IBookService {
    List<Book> findAll();

    String borrowBook(Long bookId);

    void returnBook(String code);
}
