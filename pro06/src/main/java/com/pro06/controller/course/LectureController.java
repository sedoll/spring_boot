package com.pro06.controller.course;

import com.pro06.dto.VideoDto;
import com.pro06.dto.LectureVO;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import com.pro06.service.LectureServiceImpl;
import com.pro06.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// 강의 컨트롤러

@Controller
@Log4j2
@RequestMapping("/lecture/*")
public class LectureController {

    // 실제 업로드 디렉토리
    // thymeleaf 에서는 외부에 지정하여 사용해야 한다.
    // jsp와는 다르게 webapp이 없기 때문이다.
    // resources는 정적이라 업데이트 되어도 파일을 못 찾기에 서버를 재 시작 해야함
    @Value("${uploadPath}")
    String uploadFolder;

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private UserService userService;

    // 강의 상세
    @GetMapping("detail")
    public String lectureDetail(@RequestParam("cno") Integer cno, @RequestParam("lno") Integer lno, Model model) {
        Lecture lecture = lectureService.getLecture(cno, lno);
        log.error(lecture);
        model.addAttribute("lecture", lecture);
        return "lecture/lectureDetail";
    }
    
    // 강의 생성폼 이동
    @GetMapping("insert")
    public String lectureInsert(Principal principal, @RequestParam("cno") Integer cno, Model model) {
        String id = principal.getName();

        model.addAttribute("id", id);
        model.addAttribute("cno", cno);
        return "lecture/lectureInsert";
    }
    
    // 강의 생성
    // 강의 정보, 강좌 번호, 파일 추출
    @PostMapping("insert")
    public String lectureInsert(Lecture lecture, @RequestParam("cno") Integer cno,
                                @RequestParam("attach_file") MultipartFile[] attachFiles,
                                HttpServletRequest req) throws Exception {

        // 만약 저장 폴더가 없다면 생성
        File folder = new File(uploadFolder);
        if (!folder.exists()) folder.mkdirs();
        
        // log 출력
        log.info("-----------------------------------");
        log.info(" 현재 프로젝트 홈 : " + req.getContextPath());
        log.info(" 요청 URL : " + req.getServletPath());
        log.info(" 파일 저장 경로 : " + uploadFolder);

        //여러 파일 반복 저장
        List<Video> fileList = new ArrayList<>();
        
        // 첨부된 파일(MultipartFile)을 처리할 수 있습니다.
        if (attachFiles != null && attachFiles.length > 0) {
            for (MultipartFile file : attachFiles) {
                // 파일 처리 로직 시작
                String randomUUID = UUID.randomUUID().toString();  // 파일 이름 중복 방지를 위한 랜덤 UUID 생성
                String OriginalFilename = file.getOriginalFilename();  // 실제 파일 이름
                String Extension = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));  // 파일 확장자 추출
                String saveFileName = randomUUID + Extension;  // 저장할 파일 이름 생성

                // ... (기존 파일 처리 로직)
                Video data = new Video();
                data.setSavefolder(uploadFolder);
                data.setOriginfile(file.getOriginalFilename());
                data.setSavefile(saveFileName);
                data.setFilesize(file.getSize());
                Date today = new Date();
                fileList.add(data);

                // 파일 저장
                File saveFile = new File(uploadFolder, saveFileName);
                try {
                    file.transferTo(saveFile);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    // 예외 처리
                }
            }
        }

        LectureVO lectureVO = new LectureVO();
        lectureVO.setLecture(lecture);
        lectureVO.setFileList(fileList);
        lectureVO.setCno(cno);
        lectureService.LectureVoInsert(lectureVO);
        
        // 기존 강의 저장 코드
//        lectureService.LectureInsert(lecture, cno);
        return "redirect:/course/detail?no=" + cno;
    }
}
