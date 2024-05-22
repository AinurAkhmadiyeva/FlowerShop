package FinalProject.FlowerShop.controller;

import FinalProject.FlowerShop.dto.UserDto;
import FinalProject.FlowerShop.service.FlowerService;
import FinalProject.FlowerShop.service.OrderService;
import FinalProject.FlowerShop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;

@Controller
public class HomeController {
    private final UserService userService;
    private final FlowerService flowerService;
    private final OrderService orderService;

    public HomeController(UserService userService, FlowerService flowerService, OrderService orderService) {
        this.userService = userService;
        this.flowerService = flowerService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("flowers", flowerService.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            userDto.setRoles(Collections.singleton("ROLE_USER"));
        }
        userService.registerUser(userDto);
        return "redirect:/login";
    }
        @GetMapping("/default")
        public String defaultAfterLogin(Authentication authentication) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/admin";
            }
            return "redirect:/user";
        }

        @GetMapping("/403")
        public String accessDenied() {
            return "403"; // Имя вашего шаблона для страницы 403
        }


    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("flowers", flowerService.findAll());
        return "admin";
    }

    @GetMapping("/user")
    public String userHome(Model model, Principal principal) {
        UserDto userDto = userService.findByUsername(principal.getName());
        model.addAttribute("orders", orderService.findOrdersByUser(userDto));
        return "user";
    }
}
