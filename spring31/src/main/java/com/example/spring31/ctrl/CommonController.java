package com.example.spring31.ctrl;

import com.example.spring31.dto.FileDTO;
import com.example.spring31.dto.FileVO;
import com.example.spring31.dto.Product;
import com.example.spring31.service.ProductService;
import com.example.spring31.service.UserService;
import com.example.spring31.utils.Page;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/common/*")
public class CommonController {

    // 실제 업로드 디렉토리
    // thymeleaf 에서는 외부에 지정하여 사용해야 한다.
    // jsp와는 다르게 webapp이 없기 때문이다.
    // resources는 정적이라 업데이트 되어도 파일을 못 찾기에 서버를 재 시작 해야함
    @Value("${spring.servlet.multipart.location}")
    String uploadFolder;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    // region 로그인
    // 로그인 창
    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/";
    }
    
    // region 회원가입
    //회원가입 폼 로딩
    @GetMapping("/join")
    public String joinFormLoad(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "join";
        return "redirect:/";
    }

    // 중고 게시글 관련 파일
    // 다른 디렉토리에 저장된 이미지 보기
    @GetMapping("file")
    public ResponseEntity<Resource> download(@ModelAttribute FileDTO dto) throws IOException {
        Path path = Paths.get(uploadFolder + "/" + dto.getSavefile());
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getSavefile(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


    // region board 게시판
    // 상품 목록 보기
    @GetMapping("productList")
    public String productList(HttpServletRequest request, Model model) throws Exception {
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        Page page = new Page(curPage, type, keyword, category);
        page.makePage(productService.getTotal(page));

        List<Product> productList = productService.productListWithPage(page);
        List<FileDTO> fileList = new ArrayList<>();
        for (Product pro:productList) {
            FileDTO dto = productService.thmbn(pro.getNo());
            fileList.add(dto);
        }
//        log.info(fileboardList.toString());
        model.addAttribute("productList", productList);
        model.addAttribute("fileList", fileList);
        model.addAttribute("page", page);
        return "product/productList";
    }

    // getFileboard
    // 판매 페이지 상세
    @GetMapping("getProduct")
    public String getFileboard(@RequestParam("no") int no, Model model, HttpServletRequest req) throws Exception {
        FileVO fileboard = new FileVO();

        HttpSession session = req.getSession();
        Cookie[] cookieFromRequest = req.getCookies();
        String cookieValue = null;
        for(int i = 0 ; i<cookieFromRequest.length; i++) {
            // 요청정보로부터 쿠키를 가져온다.
            cookieValue = cookieFromRequest[0].getValue();  // 테스트라서 추가 데이터나 보안사항은 고려하지 않으므로 1번째 쿠키만 가져옴
        }

        // 쿠키 세션 입력
        if (session.getAttribute(no+":cookieFile") == null) {
            session.setAttribute(no+":cookieFile", no + ":" + cookieValue);
        } else {
            session.setAttribute(no+":cookieFile ex", session.getAttribute(no+":cookieFile"));
            if (!session.getAttribute(no+":cookieFile").equals(no + ":" + cookieValue)) {
                session.setAttribute(no+":cookieFile", no + ":" + cookieValue);
            }
        }

        // 쿠키와 세션이 없는 경우 조회수 카운트
        if (!session.getAttribute(no+":cookieFile").equals(session.getAttribute(no+":cookieFile ex"))) {
            productService.countUp(no);
        }

        Product product = productService.getProduct(no); // 중고 게시글 관련 정보
        List<FileDTO> fileList = productService.getFileGroupList(no); // 해당 게시글의 파일 목록
        fileboard.setFileBoard(product);
        fileboard.setFileList(fileList);

        log.info(fileboard.toString());
        model.addAttribute("product", fileboard.getFileBoard());
        model.addAttribute("fileList", fileboard.getFileList());
        return "product/getProduct";
    }
}
