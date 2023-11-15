package com.chunjae.jpa1.biz;

import com.chunjae.jpa1.dto.BoardDTO;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;
import com.chunjae.jpa1.dto.ReplyDTO;
import com.chunjae.jpa1.entity.Board;
import com.chunjae.jpa1.entity.Reply;
import com.chunjae.jpa1.per.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 생성자 생성
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ModelMapper modelMapper;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) { // select
        Optional<Reply> result = replyRepository.findById(rno); // 데이터 검색
        Reply reply = result.orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void update(ReplyDTO replyDTO) { // 수정
        Optional<Reply> result = replyRepository.findById(replyDTO.getRno()); // 데이터 검색
        Reply reply = result.orElseThrow();
        reply.changText(replyDTO.getReplyText()); // 값 변경
        replyRepository.save(reply); // 저장
    }

    @Override
    public void delete(Long rno) { // 제거
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("rno"); // 페이징 처리

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable); // 검색
        List<ReplyDTO> dtoList = result.getContent().stream()
                .map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList()); // 결과 추출

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build(); // 결과값 리턴
    }
}
