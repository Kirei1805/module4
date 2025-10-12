package example.book.service;

import example.book.exception.InvalidBorrowCodeException;
import example.book.exception.OutOfStockException;
import example.book.model.Book;
import example.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final Map<String, Long> borrowCodes = new HashMap<>();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách!"));

        if (book.getQuantity() == 0) {
            throw new OutOfStockException("Sách đã hết, không thể mượn!");
        }

        // Giảm số lượng còn lại
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Sinh mã mượn sách 5 chữ số ngẫu nhiên
        String code = String.format("%05d", new Random().nextInt(100000));
        borrowCodes.put(code, bookId);

        return code;
    }

    @Override
    public void returnBook(String code) {
        if (!borrowCodes.containsKey(code)) {
            throw new InvalidBorrowCodeException("Mã mượn sách không hợp lệ!");
        }

        Long bookId = borrowCodes.remove(code);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách!"));

        // Tăng lại số lượng khi trả sách
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
}
