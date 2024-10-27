package lk.itum.BookTreasury.service;

import lk.itum.BookTreasury.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto);

    void deleteOrder(String orderId);

    void updateOrder(OrderDto orderDto);

    OrderDto searchOrder(String orderId);

    List<OrderDto> getAllOrders();
}
