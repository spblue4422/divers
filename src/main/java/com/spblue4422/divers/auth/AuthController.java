package com.spblue4422.divers.auth;

import com.spblue4422.divers.Dto.BasicResponseDto;
import com.spblue4422.divers.Dto.auth.LoginRequestDto;
import com.spblue4422.divers.Dto.auth.RegisterRequestDto;
import com.spblue4422.divers.users.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, "fail");
        }
    }
}
