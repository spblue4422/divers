package com.spblue4422.divers.users;

import com.spblue4422.divers.Dto.users.AddUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserInfoDetail(String userId) {
        return userRepository.findUserByUserId(userId).orElse(null);
    }

    public int insertUser(AddUserRequestDto req) {
        return 0;
    }

    public int deleteUser(Long id, String userId, String password) {
        return 0;
    }

    public int updateUser() {
        return 0;
    }
}
