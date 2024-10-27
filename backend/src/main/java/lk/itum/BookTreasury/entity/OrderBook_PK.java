package lk.itum.BookTreasury.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderBook_PK implements Serializable {
    private String orderId;
    private String isbnNo;

}
