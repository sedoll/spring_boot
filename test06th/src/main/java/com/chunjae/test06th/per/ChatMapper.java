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
    // 채팅방 목록
    List<ChatRoom> findAllRoom();
    
    // 채팅방 생성
    int createRoom(ChatRoom chatRoom);
    
    // 채팅 내역 저장
    int insertChat(ChatMessage chatMessage);

    // 채팅방의 채팅 내역 갖고오기
    List<ChatMessage> findChatById(String roomId);
    
    // 입력된 채팅 불러오기
    ChatMessage findChatLatest(String roomId);
    
    // 중복된 채팅방이 있는지 검사
    int findChatDist(ChatRoom chatRoom);

    // 구매자와의 목록 출력
    List<ProductChat> findBuyerById(String seller);

    // 판매자와의 목록 출력
    List<ProductChat> findSellerById(String buyer);
}
