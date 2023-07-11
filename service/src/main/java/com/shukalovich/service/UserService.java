package com.shukalovich.service;

import com.shukalovich.database.dto.LoginDto;
import com.shukalovich.database.dto.RegistrationDto;
import com.shukalovich.database.entity.Address;
import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.shukalovich.database.entity.enam.Role.USER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByEmailAndPass(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
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

    public Optional<UserEntity> getBy(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
    }

    public Optional<UserEntity> update(UserEntity user) {
        return update(user);
    }

    public boolean delete(Long id) {
        Optional<UserEntity> removedUser = userRepository.findById(id);
        userRepository.delete(removedUser.get());
        return true;
    }

    public Optional<UserEntity> createUser(RegistrationDto registration) {
        Address address = Address.builder()
                .city(registration.city())
                .street(registration.street())
                .building(registration.building())
                .flat(registration.flat())
                .build();
        UserEntity user = UserEntity.builder()
                .name(registration.name())
                .surname(registration.surname())
                .email(registration.email())
                .password(passwordEncoder.encode(registration.password()))
                .gender(registration.gender())
                .role(USER)
                .address(address)
                .build();

        return Optional.of(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userEntity -> User.builder()
                        .username(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(userEntity.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}