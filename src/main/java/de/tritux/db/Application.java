package de.tritux.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tritux.db.Auth.Role;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@EnableTransactionManagement
@SpringBootApplication
public class Application {
    @Autowired
    private UserRepository userRepository;

    
    public void initUsers() {
        if (userRepository.count() == 0) {
            List<User> users = Stream.of(
                new User(null, "med", "benslimen","m@gmail.com", 22781222L,"BENSLI12345", Role.ADMIN),
                new User(null, "xxx", "benslimenx","x@gmail.com", 2222222L,"bensli12345", Role.USER),
                new User(null, "yyy", "benslimeny","y@gmail.com", 3333333L,"bens12345", Role.RECRUTEUR),
                new User(null, "zzzz", "benslimenz","z@gmail.com", 4444444L,"bensli222", Role.CANDIDAT)
            ).collect(Collectors.toList());
            userRepository.saveAll(users);
        }
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}











