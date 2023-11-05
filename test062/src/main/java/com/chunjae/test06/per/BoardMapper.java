package com.chunjae.test06.per;

import com.chunjae.test06.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList();
    Board getBoard(String name);
    int getWithdraw(Integer id);
    int getActivate(String name);
    int getDormant(String name);
    Board getByEmail(String email);
    Board getByName(String name);
    Board findById(String email, String tel);
    Board findByPw(String email, String tel, String name);
    int BoardJoin(Board Board);
    int updatBoard(Board Board);
    int updateLevel(String name, String lev);
    int removBoard(String name);
    Board getBoardById(Integer id);
    Board getBoardByName(String name);
    int updatePasswordNoChange(Board Board);
}