package lk.itum.BookTreasury.controller;

import lk.itum.BookTreasury.dto.BookDto;
import lk.itum.BookTreasury.service.BookService;
import lk.itum.BookTreasury.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllBooks() {
        return new ResponseUtil(200, "Ok", bookService.getAllBooks());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveBook(@RequestBody BookDto bookDto) {
        bookService.saveBook(bookDto);
        return new ResponseUtil(200, "Saved Successfully", null);
    }

    @GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchBook(@PathVariable String isbn) {
        return new ResponseUtil(200, "Ok", bookService.searchBook(isbn));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateBook(@RequestBody BookDto bookDto) {
        bookService.updateBook(bookDto);
        return new ResponseUtil(200, "Updated Successfully", null);
    }

    @DeleteMapping(params = {"isbn"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
