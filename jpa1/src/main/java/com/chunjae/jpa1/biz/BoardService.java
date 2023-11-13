package com.chunjae.jpa1.biz;

import com.chunjae.jpa1.dto.BoardDTO;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

public interface BoardService {
    public Long register(BoardDTO boardDTO);
    public BoardDTO selectOne(Long seq);
    public void update(BoardDTO boardDTO);
    public void delete(Long seq);
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
