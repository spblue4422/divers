package com.spblue4422.divers.users;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.dto.auth.*;
import com.spblue4422.divers.dto.users.UserInfoBriefResponseDto;
import com.spblue4422.divers.dto.users.UserInfoDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserInfoBriefResponseDto getUserInfo(String userId, String type) {
        User userData = userRepository.findUserByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new BadRequestException(400, "존재하지 않는 ID입니다."));

        if(type.equals("detail")) {
            return UserInfoDetailResponseDto.builder()
                    .id(userData.getId())
                    .userId(userData.getUserId())
                    .nickName(userData.getNickName())
                    .firstName(userData.getFirstName())
                    .lastName(userData.getLastName())
                    .build();
        } else if(type.equals("brief")) {
            return UserInfoBriefResponseDto.builder()
                    .id(userData.getId())
                    .userId(userData.getUserId())
                    .nickName(userData.getNickName())
                    .build();
        } else {
            throw new BadRequestException(400, "\"" + type + "\"" + "은 잘못된 type 요청입니다.");
        }
    }

    //굳이 아래처럼 따로 method를 만들지 않아도 될듯?
/*    public UserInfoBriefResponseDto getUserInfoBrief(String userId) {
        User userData = userRepository.findUserByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new BadRequestException(400, "존재하지 않는 ID입니다."));
        if(userData == null) {
            return null;
        }

        return UserInfoBriefResponseDto.builder()
                .id(userData.getId())
                .userId(userData.getUserId())
                .nickName(userData.getNickName())
                .build();
    }

    public UserInfoDetailResponseDto getUserInfoDetail(String userId) {
        User userData = userRepository.findUserByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new BadRequestException(400, "존재하지 않는 ID입니다."));

        return UserInfoDetailResponseDto.builder()
                .id(userData.getId())
                .userId(userData.getUserId())
                .nickName(userData.getNickName())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .build();
    }*/
}
