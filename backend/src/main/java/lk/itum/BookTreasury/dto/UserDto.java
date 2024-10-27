package lk.itum.BookTreasury.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String mobileNumber;
    private String name;
    private String email;
    private String password;
}
