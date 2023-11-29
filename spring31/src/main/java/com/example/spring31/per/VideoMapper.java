package com.example.spring31.per;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper {
    @Select("select savefile from fileobj where pno = #{pno}")
    List<String> videoList(Integer pno);
}
