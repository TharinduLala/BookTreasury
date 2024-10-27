package lk.itum.BookTreasury.service;

import lk.itum.BookTreasury.dto.BookDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    void saveBook(BookDto dto);

    void deleteBook(String isbnNo);

    void updateBook(BookDto dto);

    BookDto searchBook(String isbnNo);

    List<BookDto> getAllBooks();
}
