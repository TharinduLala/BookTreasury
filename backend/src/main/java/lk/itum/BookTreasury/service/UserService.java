package lk.itum.BookTreasury.service;

import lk.itum.BookTreasury.dto.UserDto;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    void deleteUser(String mobileNumber);

    void updateUser(UserDto userDto);

    UserDto searchUser(String mobileNumber);

    List<UserDto> getAllUsers();
}
