package com.chunjae.jpa1.per;

import com.chunjae.jpa1.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.seq = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
}
