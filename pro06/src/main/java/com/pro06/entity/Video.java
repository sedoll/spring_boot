package com.pro06.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name="course")
@ToString

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class Video extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;             // 영상번호
    
    private Integer cno;            // 강좌번호
    private Integer lno;            // 강의번호
    private String savefolder;      // 저장경로
    private String originfile;      // 실제 파일 이름
    private String savefile;        // 저장된 파일 이름
    private Long filesize;          // 파일 크기
}
