package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.BoardService;
import com.chunjae.test06.biz.BoardServiceImpl;
import com.chunjae.test06.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8085")
@RequestMapping("/board/*")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    // 게시글 입력 폼 이동
    // 일치하는 데이터가 없으면 null 반환
    @GetMapping("boardInsert")
    public String boardInsertForm(@RequestParam("name") String name, Model model) throws Exception {
        model.addAttribute("name", name);
        return "board/boardInsert";
    }

    // 게시글 입력
    // 일치하는 데이터가 없으면 null 반환
    @PostMapping ("boardInsert")
    public String boardInsert(Board board, Model model) throws Exception {
        Integer ck = boardService.boardInsert(board);
        if(ck == 1) {
            log.info("게시글 작성 성공");
            return "redirect:/common/boardList";
        } else {
            log.info("게시글 작성 실패");
            return "redirect:/index";
        }
    }
}
