package lk.itum.BookTreasury.service.impl;

import jakarta.transaction.Transactional;
import lk.itum.BookTreasury.dto.UserDto;
import lk.itum.BookTreasury.entity.User;
import lk.itum.BookTreasury.repo.UserRepo;
import lk.itum.BookTreasury.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveUser(UserDto userDto) {
        if (!userRepo.existsById(userDto.getMobileNumber())) {
            userRepo.save(modelMapper.map(userDto, User.class));
        } else {
            throw new RuntimeException("This Mobile number is already registered.");
        }
    }

    @Override
    public void deleteUser(String mobileNumber) {
        if (userRepo.existsById(mobileNumber)) {
            userRepo.deleteById(mobileNumber);
        } else {
            throw new RuntimeException("No such user for this mobile number");
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        if (userRepo.existsById(userDto.getMobileNumber())) {
            userRepo.save(modelMapper.map(userDto, User.class));
        } else {
            throw new RuntimeException("No such user for this mobile number");
        }
    }

    @Override
    public UserDto searchUser(String mobileNumber) {
        if (userRepo.existsById(mobileNumber)) {
            return modelMapper.map(userRepo.findById(mobileNumber), UserDto.class);
        } else {
            throw new RuntimeException("No such user for this mobile number");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<List<UserDto>>() {
        }.getType());
    }
}
