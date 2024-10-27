package lk.itum.BookTreasury.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseUtil {
    private int code;
    private String message;
    private Object data;
}
