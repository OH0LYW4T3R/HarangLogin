package org.example.haranglogin.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomMember extends User {
    private Long id; // memberId
    private String name;
    private String phoneNumber;
    public CustomMember(
            String studentNumber,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(studentNumber, password, authorities);
    }
}
