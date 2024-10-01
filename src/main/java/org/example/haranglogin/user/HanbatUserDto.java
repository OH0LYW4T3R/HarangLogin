package org.example.haranglogin.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HanbatUserDto {
    private Long hanbatUserId;
    private String studentNumber;
    private String password;
    private String name;
    private String phoneNumber;
}
