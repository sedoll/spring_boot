package com.chunjae.jpa1.biz;

import com.chunjae.jpa1.dto.ReplyDTO;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;

public interface ReplyService {
    public Long register(ReplyDTO replyDTO);
    public ReplyDTO read(Long rno);
    public void update(ReplyDTO replyDTO);
    public void delete(Long rno);
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
