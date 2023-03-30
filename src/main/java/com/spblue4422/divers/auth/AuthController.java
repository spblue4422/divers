package com.spblue4422.divers.auth;

import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.auth.*;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;
    //private final UserService userService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        //this.userService = userService;
    }

    @PostMapping("/login")
    public BasicResponseDto login(@RequestBody LoginRequestDto req) {
        try {
            //반환을 어떻게 해야할까?
            User resData = authService.authLogin(req);


            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PostMapping("/register")
    public BasicResponseDto register(@RequestBody RegisterRequestDto req) {
        try {
            if(authService.isLoginIdExist(req.getLoginId())) {
                return BasicResponseDto.makeRes(null, 300, "ID 중복");
            }

            if(authService.isNickNameExist(req.getNickName())) {
                return BasicResponseDto.makeRes(null, 300, "닉네임 중복");
            }

            User resData = authService.authRegister(req);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PatchMapping("/modify")
    public BasicResponseDto modify(String loginId) {
        try {
            return BasicResponseDto.makeRes(null, 200, "success");
        } catch (Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @DeleteMapping("/withdrawal")
    public BasicResponseDto withdrawal(String loginId) {
        try {
            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }
}
