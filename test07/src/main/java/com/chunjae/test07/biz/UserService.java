package com.chunjae.test07.biz;

import com.chunjae.test07.domain.UserPrincipal;
import com.chunjae.test07.entity.Role;
import com.chunjae.test07.entity.UserRole;
import com.chunjae.test07.entity.Userinfo;
import com.chunjae.test07.persistence.RoleMapper;
import com.chunjae.test07.persistence.UserMapper;
import com.chunjae.test07.persistence.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    // 로그인
    public Userinfo findUserByLoginId(String id) {
        return userMapper.getUser(id);
    }
    
    // user 저장
    public void saveUser(Userinfo user) {
        user.setPw(passwordEncoder.encode(user.getPw()));
        user.setActive(1);
        userMapper.insert(user);
        Role role = roleMapper.getRoleInfo("ADMIN");
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getUserId());
        userRoleMapper.setUserRoleInfo(userRole);
    }
    
    // 사용자 이름으로 로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userinfo user = userMapper.getUser(username);
        return new UserPrincipal(user);
    }
}
