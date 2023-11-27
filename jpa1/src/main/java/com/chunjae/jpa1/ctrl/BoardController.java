package com.chunjae.jpa1.ctrl;

import com.chunjae.jpa1.biz.BoardService;
import com.chunjae.jpa1.dto.BoardDTO;
import com.chunjae.jpa1.dto.PageRequestDTO;
import com.chunjae.jpa1.dto.PageResponseDTO;
import lombok.Builder;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("list")
    public String list(PageRequestDTO pageRequestDTO, Model model) throws Exception {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "board/list";
    }

    @GetMapping("register")
    public String register(BoardDTO boardDTO, Model model) throws Exception {
        Long seq = boardService.register(boardDTO);
        model.addAttribute("seq", seq);
        return "board/register";
    }

    @GetMapping("getBoard")
    @ResponseBody
    public BoardDTO selectOne(@RequestParam("seq") Long seq, Model model) throws Exception {
        BoardDTO boardDTO = boardService.selectOne(seq);
//        model.addAttribute("boardDTO", boardDTO);
//        return "board/getBoard";
        return boardDTO;
    }

    @GetMapping("boardUpdate")
    public String boardUpdate(BoardDTO boardDTO, Model model) throws Exception {
        boardService.update(boardDTO);
        return "redirect:/board/getBoard?seq="+boardDTO.getSeq();
    }

    @GetMapping("boardDelete")
    public String boardDelete(@RequestParam("seq") Long seq, Model model) throws Exception {
        boardService.delete(seq);
        return "board/list";
    }

    @OneToMany(mappedBy = "board",cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<BoardImage> imageSet = new HashSet<>();
    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid).fileName(fileName)
                .board(this).ord(imageSet.size()).build();
        imageSet.add(boardImage);
    }
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }

}
