package com.test01.ctrl;

import com.test01.dto.BoardDto;
import com.test01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardCtrl {
    @Autowired
    private BoardService boardService;
    
    // 글 목록
    @GetMapping("list")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    // 글 상세
    @GetMapping("read")
    public String read(@RequestParam("bno") Integer bno, Model model) {
        BoardDto dto = boardService.findById(bno);
        model.addAttribute("dto", dto);
        return "board/read";
    }
    
    // 글 작성 폼 이동
    @GetMapping("write")
    public String write() {
        return "board/write";
    }

    // 글 작성
    @PostMapping("register")
    public String register(BoardDto boardDto) {
        Integer bno = boardService.register(boardDto);
        return "redirect:/board/read?bno="+bno;
    }
    
    // 글 수정 폼 이동
    @GetMapping("modify")
    public String modify(@RequestParam("bno") Integer bno, Model model) {
        BoardDto dto = boardService.findById(bno);
        model.addAttribute("dto", dto);
        return "board/modify";
    }
    
    // 글 수정
    @PostMapping("modify")
    public String modify(BoardDto boardDto) {
        boardService.modify(boardDto);
        return "redirect:/board/read?bno="+boardDto.getBno();
    }
    
    // 삭제
    @GetMapping("remove")
    public String remove(@RequestParam("bno") Integer bno) {
        boardService.remove(bno);
        return "redirect:/board/list";
    }
}
