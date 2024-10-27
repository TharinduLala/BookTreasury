package lk.itum.BookTreasury.service.impl;

import jakarta.transaction.Transactional;
import lk.itum.BookTreasury.dto.OrderDetailDto;
import lk.itum.BookTreasury.dto.OrderDto;
import lk.itum.BookTreasury.entity.Book;
import lk.itum.BookTreasury.entity.Order;
import lk.itum.BookTreasury.entity.OrderDetail;
import lk.itum.BookTreasury.entity.User;
import lk.itum.BookTreasury.repo.OrderRepo;
import lk.itum.BookTreasury.service.BookService;
import lk.itum.BookTreasury.service.OrderService;
import lk.itum.BookTreasury.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepo orderRepo, ModelMapper modelMapper, UserService userService,BookService bookService) {
        this.orderRepo = orderRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bookService=bookService;
    }

    @Override
    public void saveOrder(OrderDto orderDTO) {
        if (orderRepo.findById(orderDTO.getOrderId()).isEmpty()) {
            if (orderDTO.getOrderDetails().isEmpty()) throw new RuntimeException("No Products Added for this Order");
            User user = modelMapper.map(userService.searchUser(orderDTO.getUserMobileNumber()), User.class);
            if (Objects.isNull(user)) {
                throw new RuntimeException("No customer registered for this mobile number");
            } else {
                Order order = modelMapper.map(orderDTO, Order.class);
                order.setUser(user);

                List<OrderDetail> orderDetails = new ArrayList<>();
                for (OrderDetailDto orderDetailsDTO : orderDTO.getOrderDetails()) {
//                    OrderDetail details = modelMapper.map(orderDetailsDTO, OrderDetail.class);
                    OrderDetail details=new OrderDetail();
                    details.setOrderId(orderDTO.getOrderId());
                    details.setIsbnNo(orderDetailsDTO.getIsbnNo());
                    details.setQuantity(orderDetailsDTO.getQuantity());
                    details.setPrice(orderDetailsDTO.getPrice());
                    details.setOrder(order);
                    Book book = modelMapper.map(bookService.searchBook(orderDetailsDTO.getIsbnNo()), Book.class);
                    details.setBook(book);
                    orderDetails.add(details);
                }
                order.setOrderDetails(orderDetails);

                orderRepo.save(order);
            }
        } else {
            throw new RuntimeException("Failed to Save order! ");
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        if (orderRepo.existsById(orderId)) {
            orderRepo.deleteById(orderId);
        } else {
            throw new RuntimeException("Delete Order Failed..!, Order ID " + orderId + " Not Exist..!");
        }

    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        if (orderRepo.findById(orderDto.getOrderId()).isPresent()) {
            User user = modelMapper.map(userService.searchUser(orderDto.getUserMobileNumber()), User.class);
            if (orderDto.getOrderDetails().isEmpty()) throw new RuntimeException("No Products Added for this Order");
            Order order = modelMapper.map(orderDto, Order.class);
            order.setUser(user);

            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetailDto orderDetailsDTO : orderDto.getOrderDetails()) {
                OrderDetail details = modelMapper.map(orderDetailsDTO, OrderDetail.class);
                details.setOrder(order);
                orderDetails.add(details);
            }
            order.setOrderDetails(orderDetails);
            orderRepo.deleteById(orderDto.getOrderId());
            orderRepo.save(order);
        } else {
            throw new RuntimeException("Purchase Order Failed..!, Order ID " + orderDto.getOrderId() + " Not Exist.!");
        }
    }

    @Override
    public OrderDto searchOrder(String orderId) {
        if (orderRepo.findById(orderId).isPresent()) {
            Order order = orderRepo.findById(orderId).get();
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setTotalCost(order.getTotalCost());
            orderDto.setShippingAddress(order.getShippingAddress());
            orderDto.setUserMobileNumber(order.getUser().getMobileNumber());
            List<OrderDetailDto> orderDetails = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                OrderDetailDto details = modelMapper.map(orderDetail, OrderDetailDto.class);
                orderDetails.add(details);
            }
            orderDto.setOrderDetails(orderDetails);
            return orderDto;
        } else {
            throw new RuntimeException("Search Order Failed..!, Order ID " + orderId + " Not Exist.!");
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> allOrders = new ArrayList<>();
        for (Order order : orderRepo.findAll()) {
            OrderDto orderDto = searchOrder(order.getOrderId());
            allOrders.add(orderDto);
        }
        return allOrders;
    }
}
