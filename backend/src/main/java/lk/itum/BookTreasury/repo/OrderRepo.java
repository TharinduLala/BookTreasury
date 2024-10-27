package lk.itum.BookTreasury.repo;

import lk.itum.BookTreasury.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,String> {
}
