package com.spblue4422.divers.users;

import com.spblue4422.divers.dto.auth.RegisterRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public User getUserInfoDetail(String userId) {
        return userRepository.findUserByUserIdAndDeletedAtNull(userId).orElse(null);
    }
}
