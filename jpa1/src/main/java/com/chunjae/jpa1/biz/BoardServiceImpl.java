package com.chunjae.jpa1.biz;

import com.chunjae.jpa1.dto.BoardDTO;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;
import com.chunjae.jpa1.entity.Board;
import com.chunjae.jpa1.per.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Long seq = boardRepository.save(board).getSeq();
        return seq;
    }

    @Override
    public BoardDTO selectOne(Long seq) { // select
        Optional<Board> result = boardRepository.findById(seq); // db 검색
        Board board = result.orElseThrow(); // 없으면 예외처리
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class); // type 변경
        return boardDTO;
    }

    @Override
    public void update(BoardDTO boardDTO) { // 수정
        Optional<Board> result = boardRepository.findById(boardDTO.getSeq()); // db 검색
        Board board = result.orElseThrow(); // 없으면 예외처리
        board.change(boardDTO.getTitle(), boardDTO.getContent()); // 값 변경
        boardRepository.save(board); // 저장
    }

    @Override
    public void delete(Long seq) {
        boardRepository.deleteById(seq);
    } // 제거

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes(); // 검색 타입
        String keyword = pageRequestDTO.getKeyword(); // 검색 키워드
        Pageable pageable = pageRequestDTO.getPageable("seq"); // 페이징 처리

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable); // 검색
        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList()); // 결과 추출

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build(); // 결과값 리턴
    }
}
