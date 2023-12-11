package com.test01.service;

import com.test01.dto.BoardDto;
import com.test01.entity.Board;
import com.test01.repo.BoardRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BoardDto> findAll() {
        List<Board> lst = boardRepo.findAll();
        List<BoardDto> boardList = lst.stream()
                .map(board -> modelMapper.map(board, BoardDto.class))
                .collect(Collectors.toList());
        return boardList;
    }

    @Override
    public BoardDto findById(Integer bno) {
        Optional<Board> board = boardRepo.findById(bno);
        BoardDto dto = modelMapper.map(board, BoardDto.class);
        return dto;
    }

    @Override
    public Integer register(BoardDto boardDto) {
        Board board = modelMapper.map(boardDto, Board.class);
        return boardRepo.save(board).getBno();
    }

    @Override
    @Transactional
    public void modify(BoardDto boardDto) {
        boardRepo.modify(boardDto.getTitle(), boardDto.getContent(), boardDto.getBno());
    }

    @Override
    public void remove(Integer bno) {
        boardRepo.deleteById(bno);
    }
}
