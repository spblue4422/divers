package com.spblue4422.divers.auth;

import com.spblue4422.divers.Dto.BasicResponseDto;
import com.spblue4422.divers.Dto.auth.LoginRequestDto;
import com.spblue4422.divers.Dto.auth.RegisterRequestDto;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public BasicResponseDto login(@RequestBody LoginRequestDto req) {
        try {

            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, "Err");
        }
    }

    @PostMapping("/register")
    public BasicResponseDto signUp(@RequestBody RegisterRequestDto req) {
        try {
            if(userService.isUserIdExist(req.getUserId())) {
                return BasicResponseDto.makeRes(null, 300, "ID 중복");
            }

            if(userService.isNickNameExist(req.getNickName())) {
                return BasicResponseDto.makeRes(null, 300, "닉네임 중복");
            }

            int a = userService.insertUser(req);

            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, "fail");
        }
    }
}
