package com.example.auth.domain.member.member.entity;

import com.example.auth.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTime {
    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 50)
    private String password;

    @Column(unique = true, length = 50)
    private String apiKey;

    @Column(length = 30)
    private String nickname;

    public String getName() {
        return this.nickname;
    }

    public boolean isAdmin() {
        return "admin".equals(username);
    }
}

