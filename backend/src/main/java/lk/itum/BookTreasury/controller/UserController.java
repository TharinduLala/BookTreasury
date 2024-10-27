package lk.itum.BookTreasury.controller;

import lk.itum.BookTreasury.dto.UserDto;
import lk.itum.BookTreasury.service.UserService;
import lk.itum.BookTreasury.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllUsers() {
        return new ResponseUtil(200, "Ok", userService.getAllUsers());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseUtil(200, "Saved Successfully", null);
    }

    @GetMapping(params = {"mobileNo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchUser(@RequestParam String mobileNo) {
        return new ResponseUtil(200, "Ok", userService.searchUser(mobileNo));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseUtil(200, "Updated Successfully", null);
    }

    @DeleteMapping(params = {"mobileNo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteUser(@RequestParam String mobileNo) {
        userService.deleteUser(mobileNo);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }

}
