package com.example.spring31.service;

import com.example.spring31.per.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoMapper videoMapper;

    public List<String> videoList(Integer pno) {return videoMapper.videoList(pno);}
}
