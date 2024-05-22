package FinalProject.FlowerShop.controller;

import FinalProject.FlowerShop.dto.UserDto;
import FinalProject.FlowerShop.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }
}
