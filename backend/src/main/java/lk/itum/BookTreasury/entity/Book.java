package lk.itum.BookTreasury.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "books")
public class Book {
    @Id
    private String isbnNo;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private BigDecimal price;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
