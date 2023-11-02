package com.chunjae.test07.domain;

import com.chunjae.test07.entity.Userinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

// 스프링 security 에서 기본으로 제공하는 인터페이스
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPrincipal implements UserDetails {

    private Userinfo userinfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserGrant());
    }

    @Override
    public String getPassword() {
        return userinfo.getPw();
    }

    @Override
    public String getUsername() {
        return userinfo.getUserName();
    }

    // 계정이 있는지 없는지 확인 없으면 true, 있으면 false
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    // 인가된 사람만 호출 가능
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용 권한 있음 1, 없음 0
    @Override
    public boolean isEnabled() {
        return userinfo.getActive() == 1;
    }

    // 로그인 아이디 반환
    public String getId() {
        return userinfo.getId();
    }
    
    // name 반환
    public String getName() {
        return userinfo.getUserName();
    }
}
