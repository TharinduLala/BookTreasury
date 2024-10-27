package lk.itum.BookTreasury.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private String isbnNo;
    private String title;
    private String type;
    private String author;
    private String publisher;
    private BigDecimal price;
    private byte[] image;
}
