package com.turkcell.mini_e_commere_hw2.service.domain;
import com.turkcell.mini_e_commere_hw2.entity.User;
import com.turkcell.mini_e_commere_hw2.repository.UserRepository;
import com.turkcell.mini_e_commere_hw2.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
