package lk.itum.BookTreasury.controller;

import lk.itum.BookTreasury.dto.OrderDto;
import lk.itum.BookTreasury.service.OrderService;
import lk.itum.BookTreasury.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllOrders() {
        return new ResponseUtil(200, "Ok", orderService.getAllOrders());
    }

    @GetMapping(params = {"orderId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchOrder(@RequestParam String orderId) {
        return new ResponseUtil(200, "Ok", orderService.searchOrder(orderId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return new ResponseUtil(200, "Order Successful", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateOrder(@RequestBody OrderDto orderDto) {
        orderService.updateOrder(orderDto);
        return new ResponseUtil(200, "Updated Successfully", null);
    }

    @DeleteMapping(params = {"orderId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteOrder(@RequestParam String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }

}
