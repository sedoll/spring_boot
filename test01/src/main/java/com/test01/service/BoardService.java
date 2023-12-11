package com.test01.service;

import com.test01.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> findAll();
    BoardDto findById(Integer bno);
    Integer register(BoardDto boardDto);
    void modify(BoardDto boardDto);
    void remove(Integer bno);
}
