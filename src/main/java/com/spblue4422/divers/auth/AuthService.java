package com.spblue4422.divers.auth;

import com.spblue4422.divers.dto.auth.LoginRequestDto;
import com.spblue4422.divers.dto.auth.RegisterRequestDto;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User login(LoginRequestDto req) {
        User userData = userRepository.findUserByUserIdAndDeletedAtNull(req.getUserId()).orElse(null);
        if(userData == null) {
            //잘못된 id
            return null;
        }

        if(!passwordEncoder.matches(req.getPassword(), userData.getPassword())) {
            //잘못된 비밀번호
            return null;
        }

        //jwt 토큰 반환 => security chain 구조 부터 이해하고 넘어가야할듯

        return userData;
    }

    public User insertUser(RegisterRequestDto req) {
        return userRepository.save(req.toEntity());
        //실패시 save에서 에러 throw
    }

    public int deleteUser(Long id, String userId, String password) {
        return 0;
    }

    public int updateUser() {
        return 0;
    }
}
