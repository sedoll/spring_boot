package com.chunjae.jpa1.per;

import com.chunjae.jpa1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository
// DML 기능이 들어있음
// BoardSearch 상속

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    
}
