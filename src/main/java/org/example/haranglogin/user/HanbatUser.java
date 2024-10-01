package org.example.haranglogin.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class HanbatUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hanbatUserId;
    @Column(nullable = false, unique = true)
    private String studentNumber;
    private String password;
    private String name;
    private String phoneNumber;
}
