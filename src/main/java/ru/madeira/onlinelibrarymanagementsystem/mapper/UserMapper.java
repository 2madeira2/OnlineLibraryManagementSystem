package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    List<UserDTO> toDto(List<User> users);

    default List<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    User toUser(UserDTO userDTO);

    default Set<Role> map(List<String> roles) {
        Set<Role> roleSet = new HashSet<>();
        for(String role : roles) {
            Role r = new Role();
            r.setName(role);
            roleSet.add(r);
        }
        return roleSet;
    }
}
