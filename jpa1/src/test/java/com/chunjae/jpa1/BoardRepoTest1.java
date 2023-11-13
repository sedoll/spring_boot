package com.chunjae.jpa1;

import com.chunjae.jpa1.entity.Board;
import com.chunjae.jpa1.per.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 페이징 처리 4대 클래스
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepoTest1 {

    @Autowired
    private BoardRepository boardRepository;

    // insert
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i->{
            Board board = Board.builder()
                    .title("title - "+i)
                    .content("cotent ------ "+i)
                    .writer("writer - "+i)
                    .build();
            Board result = boardRepository.save(board);
            log.info("seq : " + result.getSeq());
        });
    }

    // select
    @Test
    public void testSelectOne() { // 100번째 글 불러옴
        Long seq = 100L;

        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow(); // 처음부터 끝까지 검색
        log.info(board);
    }

    // update
    @Test
    public void testUpdate() { // 100번째 글 수정
        Long seq = 100L;
        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        board.change("update title 100", "update content 100");
        boardRepository.save(board);
    }

    // delete
    @Test
    public void testDelete() { // 1번째 글 제거
        Long seq = 1L;
        boardRepository.deleteById(seq);
    }

    // select All
    @Test
    public void testSelectAll() {
        // 페이징 처리
        // 처음부터 10개씩 가져옴
        Pageable pageable = PageRequest.of(0, 10, Sort.by("seq").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("total count: " + result.getTotalElements());
        log.info("total page: "+result.getTotalPages());
        log.info("page number:"+result.getNumber());
        log.info("page size:"+result.getSize());
        List<Board> boardList = result.getContent();
        boardList.forEach(board -> log.info(board));
    }
}
