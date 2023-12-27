package com.ex01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class MemoController {

    private static String DATA_DIR = "D:\\kim\\file\\";

    //파일 목록 불러오기
    @GetMapping("/")
    public String loadFileList(Model model){
        File dir = new File(DATA_DIR);
        File[] filedata = dir.listFiles();
        List<File> filelist = Arrays.asList(filedata);
        for(File file : filelist){
            log.info(file.getName());
        }
        model.addAttribute("filelist", filelist);
        return "index";
    }

    //파일 저장하기
    @PostMapping("/api/test1")
    public String setFile(@RequestBody Memo memo) throws Exception {
        String filename = memo.getFilename();
        String content = memo.getContent();
        OutputStream file = new FileOutputStream(DATA_DIR+filename);
        byte[] bt = content.getBytes(); //OutputStream은 바이트 단위로 저장됨
        file.write(bt);
        file.close();
        log.info("filename : "+filename);
        log.info("conetent : "+content);
        return "redirect:/";
    }

    //파일 내용 읽어오기
    @GetMapping("/api/test2")
    @ResponseBody
    public String getFile(@RequestParam("filename") String filename, Model model) throws IOException {
        log.info("filename : "+filename);
        File file = new File(DATA_DIR + filename);
        FileReader fr = new FileReader(file);
        String content = "";
        int cur = 0;
        while((cur = fr.read()) != -1){
            content = content +((char) cur);
        }
        fr.close();
        return content;
    }

    //파일 삭제
    @PostMapping("/api/test3")
    public String removeFile(@RequestBody Memo memo) throws Exception {
        String filename = memo.getFilename();
        String content = memo.getContent();
        File file = new File(DATA_DIR + filename);
        file.delete();
        return "redirect:/";
    }
}
