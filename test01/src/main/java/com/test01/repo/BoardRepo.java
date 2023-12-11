package com.test01.repo;

import com.test01.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<Board, Integer> {
    @Modifying
    @Query("update Board b set b.title = :title, b.content = :content where b.bno = :bno")
    void modify(String title, String content, Integer bno);
}
