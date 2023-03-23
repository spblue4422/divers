package com.spblue4422.divers.users;

import com.spblue4422.divers.Dto.BasicResponseDto;
import com.spblue4422.divers.Dto.users.AddUserRequestDto;
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

    // 원래 같으면 userId를 패러미터로 받지않고 쿠키로 받아와야함.
    @GetMapping("/my")
    public BasicResponseDto getMyInfo(String userId) {
        try {
            User userData = userService.getUserInfoDetail(userId);

            return BasicResponseDto.makeRes(userData, 200, "success");
        } catch(Exception err) {
            return BasicResponseDto.makeRes(null, 500, "Error");
        }
    }

//    @PostMapping("/add")
//    public BasicResponseDto addUser(@RequestBody AddUserRequestDto req) {
//        try {
//            return new BasicResponseDto(null, 200, "success");
//        } catch (Exception err) {
//            return new BasicResponseDto(null, 500, "success");
//        }
//    }
}
