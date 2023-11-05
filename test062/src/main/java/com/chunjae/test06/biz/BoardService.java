package com.chunjae.test06.biz;

import com.chunjae.test06.entity.Board;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;


public interface BoardService {

    // 게시글 목록 보기
    public List<Board> boardList();

    // 게시글 상세 보기
    public Board getBoard(Integer id);
    
    // 게시글 작성
    public Integer boardInsert(Board board);
}
