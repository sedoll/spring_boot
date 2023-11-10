package com.chunjae.test06th.per;

import com.chunjae.test06th.entity.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {
    School getSchool(String sc_name);
}
