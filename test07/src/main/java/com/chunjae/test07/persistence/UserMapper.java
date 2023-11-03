package com.chunjae.test07.persistence;

import com.chunjae.test07.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // @Component : @Autowired 처럼 하나로 묶어줌
@Mapper
public interface UserMapper {
    @Select("select * from userinfo where loginId != null and loginId != '' and loginId = #{loginId}")
    public Userinfo getUser(String loginId);

    @Insert("insert into userinfo(active, login_id, user_name, pw) values(#{active}, #{login_id}, #{user_name}, #{pw})")
    public Integer insert(Userinfo userinfo);
}
