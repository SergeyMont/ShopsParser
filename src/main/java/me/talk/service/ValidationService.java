package me.talk.service;

import lombok.RequiredArgsConstructor;
import me.talk.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final UserRepository userRepository;

    public void validateUser(String name, String password) {

        if (!userRepository.existsByNameAndPassword(name, password)) {
            throw new RuntimeException("Wrong user");
        }
    }
}
