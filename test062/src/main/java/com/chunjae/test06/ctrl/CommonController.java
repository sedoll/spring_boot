package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.BoardServiceImpl;
import com.chunjae.test06.biz.UserService;
import com.chunjae.test06.entity.Board;
import com.chunjae.test06.entity.Euser;
import com.chunjae.test06.excep.NoSuchDataException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8085")
@RequestMapping("/common/*")
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private BoardServiceImpl boardService;

    // 로그인 창
    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/";
    }

    //아이디 로그인
    @PostMapping("/loginByName")
    public String loginByNamePro(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session, Model model){
        Euser user = userService.getByName(name);
        if(user!=null){
            if(user.getPassword().equals(password)){
                model.addAttribute("msg", "로그인 성공");
                session.setAttribute("sname", user.getName());
                session.setAttribute("slevel", user.getLev());
            } else {
                model.addAttribute("msg", "비밀번호 오류 로그인 실패");
                session.invalidate();
            }
        } else {
            model.addAttribute("msg", "해당 아이디를 가진 회원이 존재하지 않습니다.");
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원가입 폼 로딩
    @GetMapping("/join")
    public String joinFormLoad(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "join";
        return "redirect:/";
    }

    //회원가입 처리
    @PostMapping("/joinPro")
    public String joinPro(Euser euser, Model model){
        int cnt = userService.userJoin(euser);
        if(cnt == 0){
            throw new NoSuchDataException("No Insert Process Data");
        }
        return "redirect:/";
    }

    //중복 id 검증(Ajax)
    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheckAjax(@RequestBody Euser test){
        logger.info("**************** name :"+test.getName());
        boolean result = false;
        Euser user = userService.getByName(test.getName());
        if(user!=null){
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    //중복 이메일 검증(Ajax)
    @PostMapping("/emailCheck")
    @ResponseBody
    public boolean emailCheckAjax(@RequestParam("email") String email){
        Euser user = userService.getByEmail(email);
        if(user!=null){
            return false;
        } else {
            return true;
        }
    }

    // 게시글 목록 보기
    @GetMapping("boardList")
    public String boardList(Model model) throws Exception {
        List<Board> boardList = boardService.boardList();
        if(boardList.isEmpty()) { // 데이터가 없는 경우를 위해 예외처리
            throw new NoSuchFieldException("No Such List");
        }
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    // 게시글 상세 보기
    // 일치하는 데이터가 없으면 null 반환
    @GetMapping("getBoard")
    public String getBoard(@RequestParam("id") Integer id,  Model model) throws Exception {
        Board board = boardService.getBoard(id);
        log.info(board.toString());
        if(board==null) { // 회원이 없으면 예외처리, url로 직접 들어오는 것도 방지
            throw new NoSuchFieldException("No Such Data");
        }
        model.addAttribute("board", board);
        return "board/getBoard";
    }
}
