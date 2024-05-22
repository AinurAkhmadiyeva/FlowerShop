package FinalProject.FlowerShop.service;

import FinalProject.FlowerShop.dto.UserDto;
import FinalProject.FlowerShop.model.Role;
import FinalProject.FlowerShop.model.User;
import FinalProject.FlowerShop.repository.RoleRepository;
import FinalProject.FlowerShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);

        Set<Role> roles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findByName(roleName);
            roleOptional.ifPresent(roles::add);
        }

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        // Создаем и возвращаем UserDto
        return convertToDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return null;
        }
        return convertToDto(userOptional.get());
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        userDto.setRoles(roles);

        return userDto;
    }
}

