package com.spblue4422.divers.users;

import com.spblue4422.divers.Dto.auth.RegisterRequestDto;
import com.spblue4422.divers.Dto.users.AddUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //userid 중복 확인
    public Boolean isUserIdExist(String userId) {
        User findUser = userRepository.findUserByUserId(userId).orElse(null);
        return findUser != null;
    }

    public Boolean isNickNameExist(String nickName) {
        User findUser = userRepository.findUserByNickName(nickName).orElse(null);
        return findUser != null;
    }

    public User getUserInfoDetail(String userId) {
        return userRepository.findUserByUserIdAndDeletedAtNull(userId).orElse(null);
    }

    public int insertUser(RegisterRequestDto req) {
        User newUser = userRepository.save(req.toEntity());
        //실패시 에러
        
        return 0;
    }

    public int deleteUser(Long id, String userId, String password) {
        return 0;
    }

    public int updateUser() {
        return 0;
    }
}
