package com.chunjae.test06.per;

import com.chunjae.test06.entity.Board;
import com.chunjae.test06.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList(); // 리스트 목록
    Board getBoard(Integer id); // 상세
    int insertBoard(Board Board); // 추가
    int updatBoard(Board Board); // 수정
    int deleBoard(Integer id); // 삭제
    List<Comment> commentList(Integer par); // 댓글 리스트 목록
    int inserBoardCom(Comment comment); // 댓글 입력
    int getWithdraw(Integer id);
    int getActivate(String name);
    int getDormant(String name);
    Board getByEmail(String email);
    Board getByName(String name);
    Board findById(String email, String tel);
    Board findByPw(String email, String tel, String name);
    int BoardJoin(Board Board);
    int updateLevel(String name, String lev);
    int removBoard(String name);
    Board getBoardById(Integer id);
    Board getBoardByName(String name);
    int updatePasswordNoChange(Board Board);
}