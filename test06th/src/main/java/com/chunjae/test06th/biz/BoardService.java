package com.chunjae.test06th.biz;

import com.chunjae.test06th.entity.Board;
import com.chunjae.test06th.entity.Comment;

import java.util.List;


public interface BoardService {

    // 게시글 목록 보기
    public List<Board> boardList();

    // 게시글 상세 보기
    public Board getBoard(Integer id);
    
    // 게시글 작성
    public Integer insertBoard(Board board);
    public int updatBoard(Board board); // 수정
    public int deleBoard(Integer id); // 삭제

    public List<Comment> CommentList(Integer par); // 댓글 리스트 목록
    public int inserBoardCom(Comment comment); // 댓글 입력
}
