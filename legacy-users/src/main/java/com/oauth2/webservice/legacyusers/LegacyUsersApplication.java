package com.oauth2.webservice.legacyusers;

import com.oauth2.webservice.legacyusers.entity.UserEntity;
import com.oauth2.webservice.legacyusers.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class LegacyUsersApplication implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(LegacyUsersApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity user = new UserEntity(
                1L,
                "qswe3mg84mfjtu",
                "miracle",
                "Nguyen",
                "test2@test.com",
                bCryptPasswordEncoder.encode("123456@"),
                "",
                false);

        usersRepository.save(user);

    }
}
