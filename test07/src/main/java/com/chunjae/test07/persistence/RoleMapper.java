package com.chunjae.test07.persistence;

import com.chunjae.test07.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    
    // admin만 조회 가능
    @Select("select * from role where role not null and role != '' and role = #{role}")
    public List<Role> resultMap(@Param("role") String role);
    

}
