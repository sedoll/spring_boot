package com.chunjae.test06th.per;

import com.chunjae.test06th.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodMapper {
    @Select("SELECT * FROM school WHERE scname LIKE CONCAT('%', #{scname}, '%') limit 1")
    School getSchool(String scname);
}
