package lk.itum.BookTreasury.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_mobile_number", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
