package lk.itum.BookTreasury.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@IdClass(OrderBook_PK.class)
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Id
    @Column(name = "isbn_no")
    private String isbnNo;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "isbn_no", insertable = false, updatable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;


}
