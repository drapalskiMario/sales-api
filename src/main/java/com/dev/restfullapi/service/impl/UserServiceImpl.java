package com.dev.restfullapi.service.impl;

import com.dev.restfullapi.domain.entity.UserEntity;
import com.dev.restfullapi.domain.repository.UserRepository;
import com.dev.restfullapi.exception.InvalidPasswordException;
import com.dev.restfullapi.rest.dto.input.AuthDTO;
import com.dev.restfullapi.rest.dto.input.UserDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepository
                .findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var roles = user.isAdmin() ? new String[] { "ADMIN", "USER"} : new String[] { "USER" };
        return User
                .builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails authUser(AuthDTO authDTO) {
        var userDetails = this.loadUserByUsername(authDTO.getUserName());
        var passwordIsValid = this.passwordEncoder.matches(authDTO.getPassword(), userDetails.getPassword());
        if (passwordIsValid) {
            return userDetails;
        } else {
            throw new InvalidPasswordException();
        }
    }

    public void create(UserDTO userDTO) {
        var passwordEncoded = this.passwordEncoder.encode(userDTO.getPassword());
        var entity = new UserEntity(userDTO.getUserName(), passwordEncoded, userDTO.isAdmin());
        this.userRepository.save(entity);
    }
}
