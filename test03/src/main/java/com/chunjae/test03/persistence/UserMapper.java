package com.chunjae.test03.persistence;

import com.chunjae.test03.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 회원 목록 보기
    // where lev not in ('ADMIN') = 관리자 빼고 정보 출력
    @Select("select * from userinfo where lev not in ('ADMIN')")
    public List<UserInfo> userList();

    // 아이디로 회원 상세 정보 보기
    @Select("select * from userinfo where id = #{id}")
    public UserInfo getUser(String id);

    // 이메일로 회원 상세 정보 보기
    @Select("select * from userinfo where email = #{email}")
    public UserInfo getUserEmail(String email);
    
    // 회원탈퇴 act:JOIN-활동, act:DORMANT-휴면, WITHDRAW-탈퇴
    @Update("update userinfo set act='WITHDRAW' where id=#{id}")
    public void withUserAct(String id);
    
    // 휴면 처리
    @Update("update userinfo set act='DORMANT' where id=#{id}")
    public void dorUserAct(String id);
    
    // 계정 활성화
    @Update("update userinfo set act='JOIN' where id=#{id}")
    public void joinUserAct(String id);

    // 계정 삭제
    @Delete("delete from userinfo where id=#{id}")
    public int delUser(String id);
    
    // 이메일 로그인
    @Select("select * from userinfo where email=#{email}")
    public UserInfo loginEmail(String email);
    
    // 아이디 로그인
    @Select("select * from userinfo where id=#{id}")
    public UserInfo loginId(String id);
    
    // 아이디 찾기
    @Select("select * from userinfo where email=#{email} and tel=#{tel}")
    public UserInfo findById(UserInfo userInfo);
    
    // 비밀번호 찾기
    @Select("select * from userinfo where email=#{email} and tel=#{tel} and id=#{id}")
    public UserInfo findByPw(UserInfo userInfo);

    // 회원 수정
    @Update("update userinfo set pw=#{pw}, name=#{name}, email=#{email}, tel=#{tel}, addr=#{addr} where id = #{id}")
    public int updUser(UserInfo userInfo);

    // 회원 가입
    @Insert("insert into userinfo values(default, #{id}, #{pw}, #{name}, #{email}, #{addr}, #{tel}, default, default, default, default)")
    public int insUser(UserInfo userInfo);
    
//    <!-- 로그인을 컨트롤에서 처리 -->
//    <select id="signIn" resultType="kr.ed.haebeop.domain.Member">
//    select * from member order by regdate desc
//    </select>
//
//    <!-- 로그인 : DAO 에서 처리 -->
//    <select id="loginCheck" resultType="kr.ed.haebeop.domain.Member">
//    select id, pw, name from member where id = #{id}
//    </select>
//
//    <!-- 로그인 : ajax로 처리 -->
//    <select id="login" resultType="kr.ed.haebeop.domain.Member">
//    select id, pw, name from member where id = #{id}
//    </select>
//
//    <!-- 회원 정보 조회 (비밀번호 찾기 할 때 필요)-->
//    <select id="selectMember"  resultType="kr.ed.haebeop.domain.Member">
//    select * from member where email = #{email}
//    </select>
}
