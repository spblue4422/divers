package com.spblue4422.divers.auth;

import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.auth.*;
import com.spblue4422.divers.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

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
    public BasicResponseDto register(@RequestBody SaveAuthRequestDto req) {
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
    public BasicResponseDto modify(@RequestBody SaveAuthRequestDto req) {
        try {
            User resData = authService.authModify(req);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch (Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PatchMapping("/changePassword")
    public BasicResponseDto changePassword(@RequestBody LoginRequestDto req) {
        try {

            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
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
