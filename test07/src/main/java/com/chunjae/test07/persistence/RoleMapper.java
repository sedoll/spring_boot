package com.chunjae.test07.persistence;

import com.chunjae.test07.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // @Component : @Autowired 처럼 하나로 묶어줌
@Mapper
public interface RoleMapper {
    
    // admin만 조회 가능
    @Select("select * from role where role not null and role != '' and role = #{role} limit 1")
    public Role getRoleInfo(@Param("role") String role);
    

}
