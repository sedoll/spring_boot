package com.chunjae.jpa1.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

// entity는 테이블 만들려고 사용한다.

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "board") // board에 출력
@Table(name = "Reply", indexes =
    @Index(name = "idx_reply_board_bno", columnList = "board_seq"))
public class Reply extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;               // 기본키

    // @ManyToMany // n : m
    // @OneToMany // 1 : n
    // @OneToOne // 1 : 1
    @ManyToOne(fetch = FetchType.LAZY) // n : 1, 레이지 로딩을 해야 한다, 미리 테이블의 데이터를 가져옴
    private Board board;

    private String replyText;       // 답글
    private String replyer;         // 답변자

    public void changText(String replyText) { // setter
        this.replyText = replyText;
    }
}
