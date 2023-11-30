package com.pro06.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(unique = true)
    private String id;
    private String pw;
    private String name;
    @Column(nullable = true)
    private String tel;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String addr1;
    @Column(nullable = true)
    private String addr2;
    @Column(nullable = true)
    private String postcode;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;

}
