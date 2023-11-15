package com.chunjae.jpa1.ctrl;

import com.chunjae.jpa1.biz.ReplyService;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;
import com.chunjae.jpa1.dto.ReplyDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reply/*")
public class ReplyController {

    private final ReplyService replyService;

    @ApiOperation(value = "Replies POST", notes = "POST 방식") // 댓글 입력
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) // json 타입으로 데이터를 받음
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
        log.info(replyDTO);
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        resultMap.put("rno", rno);
        return resultMap;
    }

    @ApiOperation(value = "Replies of Board", notes = "GET 방식 댓글 목록")
    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return responseDTO;
    }
    @ApiOperation(value = "Read Reply", notes = "GET 방식 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO( @PathVariable("rno") Long rno ){
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }
    @ApiOperation(value = "Delete Reply", notes = "DELETE 방식 - 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String,Long> remove( @PathVariable("rno") Long rno ){
        replyService.delete(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }
    @ApiOperation(value = "Modify Reply", notes = "PUT 방식 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Map<String,Long> remove( @PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO ){
        replyDTO.setRno(rno);
        replyService.update(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }
}
