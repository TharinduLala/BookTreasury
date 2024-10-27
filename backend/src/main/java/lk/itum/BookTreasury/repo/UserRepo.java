package lk.itum.BookTreasury.repo;

import lk.itum.BookTreasury.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
