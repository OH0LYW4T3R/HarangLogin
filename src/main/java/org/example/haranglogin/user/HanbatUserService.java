package org.example.haranglogin.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.haranglogin.jwt.JwtDto;
import org.example.haranglogin.jwt.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HanbatUserService {
    private final HanbatUserRepository hanbatUserRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public JwtDto jwtCreate(HanbatUserDto memberDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                memberDto.getStudentNumber(), memberDto.getPassword()
        );

        Authentication auth = authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String jwt = jwtUtil.createToken(auth2, response);
        String jwtRefresh = jwtUtil.createRefreshToken(auth2, response);

        return jwtUtil.convertToDto(jwt, jwtRefresh);
    }

    @Transactional
    public HanbatUserDto signup(HanbatUserDto hanbatUserDto) {
        HanbatUser hanbatUser = new HanbatUser();

        hanbatUser.setStudentNumber(hanbatUserDto.getStudentNumber());
        hanbatUser.setName(hanbatUserDto.getName());
        hanbatUser.setPassword(passwordEncoder.encode(hanbatUserDto.getPassword()));
        hanbatUser.setPhoneNumber(hanbatUserDto.getPhoneNumber());

        hanbatUserRepository.save(hanbatUser);

        hanbatUserDto.setHanbatUserId(hanbatUser.getHanbatUserId());

        return hanbatUserDto;
    }
}
