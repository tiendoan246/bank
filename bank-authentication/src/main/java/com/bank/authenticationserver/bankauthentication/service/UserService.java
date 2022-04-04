package com.bank.authenticationserver.bankauthentication.service;

import com.bank.authenticationserver.bankauthentication.exception.RoleNotFoundException;
import com.bank.authenticationserver.bankauthentication.model.UserRoleEnum;
import com.bank.authenticationserver.bankauthentication.model.request.SignupRequest;
import com.bank.authenticationserver.bankauthentication.model.response.MessageResponse;
import com.bank.authenticationserver.bankauthentication.repository.RoleRepository;
import com.bank.authenticationserver.bankauthentication.repository.UserRepository;
import com.bank.authenticationserver.bankauthentication.repository.entity.Role;
import com.bank.authenticationserver.bankauthentication.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> createUser(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(request.getUsername(), encoder.encode(request.getPassword()), request.getEmail(), request.getDateOfBirth());
        Set<String> strRoles = request.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(UserRoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Optional<Role> userRole = roleRepository.findByName(UserRoleEnum.from(role));

                if (!userRole.isPresent()) {
                    throw new RoleNotFoundException(String.format("Error: Role {} is not exist!", role));
                }

                roles.add(userRole.get());
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
