package com.chunjae.test06th.per;

import com.chunjae.test06th.entity.ChatMessage;
import com.chunjae.test06th.entity.ChatRoom;
import com.chunjae.test06th.entity.ProductChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper
public interface ChatMapper {
    List<ChatRoom> findAllRoom(); // 채팅방 목록
    int createRoom(ChatRoom chatRoom); // 채팅방 생성
    int insertChat(ChatMessage chatMessage); // 채팅 내역 저장
    List<ChatMessage> findChatById(String roomId); // 채팅방의 채팅 내역 갖고오기
    int findChatDist(ChatRoom chatRoom); // 중복된 채팅방이 있는지 검사
    int actUpdate(int pno); // 거래 완료된 채팅방 숨김처리
}
