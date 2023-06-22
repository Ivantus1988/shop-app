package com.shukalovich.service;

import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByEmailAndPass(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> update(UserEntity user) {
        return update(user);
    }

    public boolean delete(Long id) {
        Optional<UserEntity> removedUser = userRepository.findById(id);
        userRepository.delete(removedUser.get());
        return true;
    }
}