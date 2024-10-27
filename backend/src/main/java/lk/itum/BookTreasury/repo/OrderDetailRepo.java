package lk.itum.BookTreasury.repo;

import lk.itum.BookTreasury.entity.OrderBook_PK;
import lk.itum.BookTreasury.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, OrderBook_PK> {
}
