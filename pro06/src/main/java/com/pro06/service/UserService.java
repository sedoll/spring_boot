package com.pro06.service;

import com.pro06.entity.MyVideo;
import com.pro06.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public User userInsert(User user);
    public PasswordEncoder passwordEncoder();
    public User getId(String id);

    // 동영상 시청 중
    void updateStudyYes(String id);

    // 동영상 시청 종료
    void updateStudyNo(String id);
}
