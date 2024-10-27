package lk.itum.BookTreasury.repo;

import lk.itum.BookTreasury.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,String> {
}
