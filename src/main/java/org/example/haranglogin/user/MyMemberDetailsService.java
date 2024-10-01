package org.example.haranglogin.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// 중간 로그인 검증 로직임
public class MyMemberDetailsService implements UserDetailsService {
    private final HanbatUserRepository hanbatUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<HanbatUser> result = hanbatUserRepository.findByStudentNumber(username);

        if(result.isEmpty())
            throw new UsernameNotFoundException("아이디가 존재하지 않거나, 비밀번호가 틀렸습니다.");

        HanbatUser member = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Normal User"));

        CustomMember customMember = new CustomMember(member.getStudentNumber(), member.getPassword(), authorities);
        customMember.setId(member.getHanbatUserId());
        customMember.setName(member.getName());
        customMember.setPhoneNumber(member.getPhoneNumber());

        // 반환하게 되면 알아서 아이디 비번 맞는지 확인해줌
        return customMember;
    }
}
