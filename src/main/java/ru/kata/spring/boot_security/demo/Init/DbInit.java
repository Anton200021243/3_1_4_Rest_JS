package ru.kata.spring.boot_security.demo.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DbInit(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        // Создаем роли
        Role roleUser = new Role(1, "ROLE_USER");
        Role roleAdmin = new Role(2, "ROLE_ADMIN");

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        // Создаем пользователей и устанавливаем роли
        User admin = new User("admin",
                "$2a$10$ej8m0g0/4nRLEbBH/bJlFuFcRK7E.huTjNe08OsCYczNyimCCJtV.", "admin@mail.ru");
        User user = new User("user",
                "$2a$10$FH9R6.Egoqxht7OeeHSAh.Y6NtwIzdAk4A598xvMlyToAfIImeUZW", "user@mail.ru");

        // Устанавливаем роли пользователям
        admin.setRoles(Set.of(roleAdmin));
        user.setRoles(Set.of(roleUser));

        userRepository.save(admin);
        userRepository.save(user);
    }
}
