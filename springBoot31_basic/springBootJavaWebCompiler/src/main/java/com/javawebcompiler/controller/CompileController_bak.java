package com.javawebcompiler.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javawebcompiler.builder.CompileBuilder;

@CrossOrigin
@RequiredArgsConstructor
public class CompileController_bak {

    private final CompileBuilder builder;

    @PostMapping(value="compile")
    public Map<String, Object> compileCode(@RequestBody Map<String, Object> input) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // byte[] rst = new byte[] {};

        Object obj = builder.compileCode(input.get("code").toString());

        // 실행 후 결과 전달 받음
        long beforeTime = System.currentTimeMillis();
        // rst = builder.runObject(obj, new byte[] {});
        String params[] = new String[] {"marina", "josipa", "nikola", "vinko", "filipa"};
        Map<String, Object> output = builder.runObject(obj, params);
        long afterTime = System.currentTimeMillis();

        returnMap.put("output", output);

        // 소요시간
        returnMap.put("performance", (afterTime - beforeTime)/1000);

        return returnMap;
    }

    @PostMapping(value="stop")
    public void stopTomcatTest() throws Exception {
        System.exit(1);
    }
}
