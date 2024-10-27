package lk.itum.BookTreasury.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private String bookName;
    private String isbnNo;
    private int quantity;
    private BigDecimal price;
}
