package com.pro06.controller.course;

import com.pro06.dto.LectureVO;
import com.pro06.entity.Course;
import com.pro06.entity.LecTest;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import com.pro06.service.course.LectureServiceImpl;
import com.pro06.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.*;

// 강의 컨트롤러

@Controller
@Log4j2
@RequestMapping("/lecture/*")
public class LectureController {

    // 실제 업로드 디렉토리
    // thymeleaf 에서는 외부에 지정하여 사용해야 한다.
    // jsp와는 다르게 webapp이 없기 때문이다.
    // resources는 정적이라 업데이트 되어도 파일을 못 찾기에 서버를 재 시작 해야함
    @Value("${spring.servlet.multipart.location}")
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
    
    // spring boot 3 이상부터 이런식으로 사용해야 함
    // 강의 생성
    // 강의 정보, 강좌 번호, 파일 추출
    @PostMapping("insert")
    public String lectureInsertPro(MultipartHttpServletRequest req) throws Exception {
        
        // 입력된 파일목록
        List<MultipartFile> files = new ArrayList<>();
        // 강의 정보
        Lecture lecture = new Lecture();
        // 여러 파일 반복 저장
        List<Video> fileList = new ArrayList<>();

        // 파일 저장
        for(int i=0; i<req.getFiles("files").size(); i++) {
            files.add(req.getFiles("files").get(i));
        }
        
        // 강의정보 저장
        Course course = new Course();
        Integer cno = Integer.parseInt(req.getParameter("cno"));
        course.setNo(cno);
        lecture.setCourse(course);      // cno 저장
        lecture.setId(req.getParameter("id"));
        lecture.setTitle(req.getParameter("title"));
        lecture.setContent(req.getParameter("content"));
        lecture.setKeyword(req.getParameter("keyword"));
        
        // 시험정보 저장
        LecTest lecTest = new LecTest();
        lecTest.setExam1(req.getParameter("exam1"));
        lecTest.setExam2(req.getParameter("exam2"));
        lecTest.setExam3(req.getParameter("exam3"));
        lecTest.setExam4(req.getParameter("exam4"));
        lecTest.setExam5(req.getParameter("exam5"));
        lecTest.setAnswer1(req.getParameter("answer1"));
        lecTest.setAnswer2(req.getParameter("answer2"));
        lecTest.setAnswer3(req.getParameter("answer3"));
        lecTest.setAnswer4(req.getParameter("answer4"));
        lecTest.setAnswer5(req.getParameter("answer5"));

        // 파일 추출 테스트
        for(int i=0; i<req.getFiles("files").size(); i++) {
            log.info("req.getParameter(\"title\") : " + req.getParameter("title"));
            log.info("req.getFile(\"files\") : " + req.getFile("files"));
            log.info("req.getFile(\"files\").getOriginalFilename() : " +
                    req.getFiles("files").get(i).getOriginalFilename());      // 실제 파일이름 출력
            log.info("req.getFiles(\"files\").get("+i+").getBytes() : " +
                    req.getFiles("files").get(i).getBytes());                 // 파일의 용량 출력
            log.info("req.getFiles(\"files\").get("+i+").getName() : " +
                    req.getFiles("files").get(i).getName());                  // name 속성값 출력
            log.info("req.getFiles(\"files\").size() : " +
                    req.getFiles("files").size());                            // 입력된 파일의 개수 출력
        }

        // 만약 저장 폴더가 없다면 생성
        File folder = new File(uploadFolder);
        if (!folder.exists()) folder.mkdirs();
        
        // log 출력
        log.info("-----------------------------------");
        log.info(" 현재 프로젝트 홈 : " + req.getContextPath());
        log.info(" 요청 URL : " + req.getServletPath());
        log.info(" 파일 저장 경로 : " + uploadFolder);
        
        // 첨부된 파일(MultipartFile) 처리
        if (files != null && files.size() > 0) {
            for (MultipartFile file : files) {
                // 파일 처리 로직 시작
                String randomUUID = UUID.randomUUID().toString();  // 파일 이름 중복 방지를 위한 랜덤 UUID 생성
                String OriginalFilename = file.getOriginalFilename();  // 실제 파일 이름
                String Extension = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));  // 파일 확장자 추출
                String saveFileName = randomUUID + Extension;  // 저장할 파일 이름 생성

                // 저장위치, 실제파일이름, 저장될 파일이름, 파일크기 정보를 저장
                Video data = new Video();
                data.setSavefolder(uploadFolder);
                data.setOriginfile(file.getOriginalFilename());
                data.setSavefile(saveFileName);
                data.setFilesize(file.getSize());
                fileList.add(data);

                // 파일 저장
                File saveFile = new File(uploadFolder, saveFileName);
                try {
                    file.transferTo(saveFile); // 실제 upload 위치에 파일 저장
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    // 예외 처리
                }
            }
        }
        
        // VO를 통해 db에 저장
        LectureVO lectureVO = new LectureVO();
        lectureVO.setLecture(lecture);
        lectureVO.setFileList(fileList);
        lectureVO.setLecTest(lecTest);
        lectureVO.setCno(cno);
        lectureService.LectureVoInsert(lectureVO); // 강의와 비디오를 같이 저장
        
        return "redirect:/course/detail?no=" + cno;
    }

    // 시험 보기
    @PostMapping("test")
    public String test(@RequestParam("cno") Integer cno,
                       @RequestParam("lno") Integer lno,
                       Principal principal, Model model){

        LecTest lecTest = lectureService.getLecTest(cno, lno);
        
        // 문제 저장
        List<String> examList = new ArrayList<>();
        examList.add(lecTest.getExam1());
        examList.add(lecTest.getExam2());
        examList.add(lecTest.getExam3());
        examList.add(lecTest.getExam4());
        examList.add(lecTest.getExam5());
        
        // 선택 답안 저장
        List<String> itemList = new ArrayList<>();
        itemList.add(lecTest.getAnswer1());
        itemList.add(lecTest.getAnswer2());
        itemList.add(lecTest.getAnswer3());
        itemList.add(lecTest.getAnswer4());
        itemList.add(lecTest.getAnswer5());
        Collections.shuffle(itemList);

        model.addAttribute("examList", examList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("lecTest", lecTest);
        return "lecture/lectureTest";
    }

    // 정답 가져오기
    @PostMapping("answers")
    @ResponseBody
    public List<String> answers(@RequestParam("cno") Integer cno,
                                @RequestParam("lno") Integer lno) {
        LecTest lecTest = lectureService.getLecTest(cno, lno);
        List<String> answers = new ArrayList<>();
        answers.add(lecTest.getAnswer1());
        answers.add(lecTest.getAnswer2());
        answers.add(lecTest.getAnswer3());
        answers.add(lecTest.getAnswer4());
        answers.add(lecTest.getAnswer5());
        return answers;
    }
}
