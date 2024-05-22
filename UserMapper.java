package FinalProject.FlowerShop.mapper;

import FinalProject.FlowerShop.dto.UserDto;
import FinalProject.FlowerShop.model.Role;
import FinalProject.FlowerShop.model.RoleName;
import FinalProject.FlowerShop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", expression = "java(stringsToRoles(userDto.getRoles()))")
    User toEntity(UserDto userDto);

    @Mapping(target = "roles", expression = "java(rolesToStrings(user.getRoles()))")
    UserDto toDto(User user);

    default Set<Role> stringsToRoles(Set<String> roleNames) {
        if (roleNames == null) {
            return Collections.emptySet();
        }
        return roleNames.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName); // Используем строку напрямую
                    return role;
                })
                .collect(Collectors.toSet());
    }

    default Set<String> rolesToStrings(Set<Role> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(Role::getName) // Имя роли уже строка
                .collect(Collectors.toSet());
    }
}
