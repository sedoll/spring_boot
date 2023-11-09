package com.chunjae.test06th.per;

import com.chunjae.test06th.entity.ChatMessage;
import com.chunjae.test06th.entity.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper
public interface ChatMapper {
    // 채팅방 목록
    List<ChatRoom> findAllRoom();
    // 채팅방 생성
    int createRoom(ChatRoom chatRoom);
    // 채팅 내역 저장
    int insertChat(ChatMessage chatMessage);
    // 입력된 채팅 불러오기
    ChatMessage findChatLatest(String roomId);
}
