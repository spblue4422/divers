package com.spblue4422.divers.auth;

import com.spblue4422.divers.dto.auth.*;
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
    public Boolean isLoginIdExist(String loginId) {
        User userData = userRepository.findUserByLoginId(loginId).orElse(null);
        return userData != null;
    }

    public Boolean isNickNameExist(String nickName) {
        User userData = userRepository.findUserByNickName(nickName).orElse(null);
        return userData != null;
    }

    public User authLogin(LoginRequestDto req) {
        User userData = userRepository.findUserByLoginIdAndDeletedAtIsNull(req.getLoginId()).orElse(null);
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

    public User authRegister(RegisterRequestDto req) {
        RegisterRequestDto reqData = RegisterRequestDto.builder()
                .loginId(req.getLoginId())
                .password(passwordEncoder.encode(req.getPassword()))
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .nickName(req.getNickName())
                .build();

        return userRepository.save(reqData.toEntity());
    }

    public int authModify() {
        return 0;
    }

    public int authWithdrawal(Long id, String userId, String password) {
        return 0;
    }
}
