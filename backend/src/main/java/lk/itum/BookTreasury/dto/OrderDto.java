package lk.itum.BookTreasury.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.itum.BookTreasury.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private LocalDate orderDate;
    private BigDecimal totalCost;
    private String shippingAddress;
    private String userMobileNumber;
    private List<OrderDetailDto> orderDetails;
}
