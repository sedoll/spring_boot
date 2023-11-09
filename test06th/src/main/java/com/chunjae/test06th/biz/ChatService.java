package com.chunjae.test06th.biz;

import com.chunjae.test06th.entity.ChatMessage;
import com.chunjae.test06th.entity.ChatRoom;
import com.chunjae.test06th.per.ChatMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;
    @Autowired
    private ChatMapper chatMapper;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
    
    // 채팅방 목록
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRoomList = chatMapper.findAllRoom();
        for (ChatRoom cr: chatRoomList) {
            chatRooms.put(cr.getRoomId(), cr);
        }
        return new ArrayList<>(chatRooms.values());
    }
    
    // 채팅방 들어가기
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }
    
    // 채팅방 만들기
    public ChatRoom createRoom(ChatRoom chatRoom) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom2 = ChatRoom.builder()
                .roomId(randomId)
                .name(chatRoom.getName())
                .build();
        chatRooms.put(randomId, chatRoom2);
        chatRoom.setRoomId(randomId);
        int ck = chatMapper.createRoom(chatRoom);

        return chatRoom;
    }

    // 채팅 내역 저장
    public int insertChat(ChatMessage chatMessage) {
        return chatMapper.insertChat(chatMessage);
    }

    // 입력된 채팅 불러오기
    public ChatMessage findChatLatest(String roomId) {
        return chatMapper.findChatLatest(roomId);
    }
}
