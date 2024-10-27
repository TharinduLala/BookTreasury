package lk.itum.BookTreasury.service.impl;

import jakarta.transaction.Transactional;
import lk.itum.BookTreasury.dto.BookDto;
import lk.itum.BookTreasury.entity.Book;
import lk.itum.BookTreasury.repo.BookRepo;
import lk.itum.BookTreasury.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepo bookRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveBook(BookDto dto) {
        if (!bookRepo.existsById(dto.getIsbnNo())) {
            bookRepo.save(modelMapper.map(dto, Book.class));
        } else {
            throw new RuntimeException("This Book is Already Added!");
        }
    }

    @Override
    public void deleteBook(String isbnNo) {
        if (bookRepo.existsById(isbnNo)) {
            bookRepo.deleteById(isbnNo);
        } else {
            throw new RuntimeException("This book is not exists!");
        }
    }

    @Override
    public void updateBook(BookDto dto) {
        if (bookRepo.existsById(dto.getIsbnNo())) {
            bookRepo.save(modelMapper.map(dto, Book.class));
        } else {
            throw new RuntimeException("No Book for this isbn to update");
        }
    }

    @Override
    public BookDto searchBook(String isbnNo) {
        if (bookRepo.existsById(isbnNo)) {
            return modelMapper.map(bookRepo.findById(isbnNo), BookDto.class);
        } else {
            throw new RuntimeException("No such book for " + isbnNo + " !");
        }
    }

    @Override
    public List<BookDto> getAllBooks() {
        return modelMapper.map(bookRepo.findAll(), new TypeToken<List<BookDto>>() {
        }.getType());
    }
}
