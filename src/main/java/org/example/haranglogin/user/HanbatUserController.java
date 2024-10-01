package org.example.haranglogin.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.haranglogin.jwt.JwtDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HanbatUserController {
    private final HanbatUserService hanbatUserService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginJWT(@RequestBody HanbatUserDto hanbatUserDto, HttpServletResponse response, HttpServletRequest request) {
        JwtDto jwtDto = hanbatUserService.jwtCreate(hanbatUserDto, response);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody HanbatUserDto hanbatUserDto) {
        HanbatUserDto createHanbatUserDto = hanbatUserService.signup(hanbatUserDto);
        return new ResponseEntity<>(createHanbatUserDto, HttpStatus.OK);
    }
}
