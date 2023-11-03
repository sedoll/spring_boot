package com.chunjae.test07.persistence;

import com.chunjae.test07.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component // @Component : @Autowired 처럼 하나로 묶어줌
@Mapper
public interface UserRoleMapper {
    @Select("")
    public void setUserRoleInfo(@Param("param")UserRole param);
}
