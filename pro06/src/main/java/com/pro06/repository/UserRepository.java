package com.pro06.repository;

import com.pro06.entity.MyVideo;
import com.pro06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select m from User m where m.id = :id")
    User getId(@Param("id") String id);
    
    
    // 동영상 시청 상태 수정
    // 동영상 시청 중
    @Modifying
    @Query("update User u set u.isStudy = 'y' where u.id = :id")
    void updateStudyYes(@Param("id") String id);
    
    // 동영상 시청 종료
    @Modifying
    @Query("update User u set u.isStudy = 'n' where u.id = :id")
    void updateStudyNo(@Param("id") String id);
}
