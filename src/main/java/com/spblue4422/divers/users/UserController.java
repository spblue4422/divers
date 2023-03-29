package com.spblue4422.divers.users;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.users.UserInfoBriefResponseDto;
import com.spblue4422.divers.dto.users.UserInfoDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 원래 같으면 userId를 패러미터로 받지않고 토큰으로 받아와야함.
    @GetMapping("/my")
    public BasicResponseDto getMyInfo(String loginId) {
        try {
            //UserInfoDetailResponseDto userData = userService.getUserInfoDetail(userId);
            UserInfoBriefResponseDto userData = userService.getUserInfo(loginId, "detail");

            return BasicResponseDto.makeRes(userData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/info/:userId")
    public BasicResponseDto getUserInfo(@RequestParam("loginId") String loginId) {
        try {
            UserInfoBriefResponseDto userData = userService.getUserInfo(loginId, "brief");

            return BasicResponseDto.makeRes(userData, 200, "success");
        } catch(Exception ex) {
            if(ex instanceof BadRequestException) {
                return BasicResponseDto.makeRes(null, ((BadRequestException) ex).getErrorCode(), ex.getMessage());
            } else {
                return BasicResponseDto.makeRes(null, 500, ex.getMessage());
            }
        }
    }
}
