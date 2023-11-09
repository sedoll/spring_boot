package com.chunjae.test06th.ctrl;

import com.chunjae.test06th.biz.ChatService;
import com.chunjae.test06th.entity.ChatMessage;
import com.chunjae.test06th.entity.ChatRoom;
import com.chunjae.test06th.utils.MessageType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat/*")
public class ChatController {

    @Autowired
    private ChatService chatService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        logger.info(roomList.toString());
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    @PostMapping("createRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoom(Model model, ChatRoom chatRoom, String username) {
        ChatRoom room = chatService.createRoom(chatRoom);
        model.addAttribute("room",room);
        model.addAttribute("username",username);
        return "chat/chatRoom";  //만든사람이 채팅방 1빠로 들어가게 됩니다
    }

    @GetMapping("chatRoom")
    public String chatRoom(Model model, @RequestParam String roomId){
        ChatRoom room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);   //현재 방에 들어오기위해서 필요한데...... 접속자 수 등등은 실시간으로 보여줘야 돼서 여기서는 못함
        return "chat/chatRoom";
    }

    @PostMapping("insertChat")
    @ResponseBody
    public void insertChat(@RequestParam("type") MessageType type, @RequestParam("roomId") String roomId,
                                  @RequestParam("sender") String id, @RequestParam("msg") String message ) {
        ChatMessage chatMsg = new ChatMessage();
        chatMsg.setRoomId(roomId);
        chatMsg.setType(type);
        chatMsg.setSender(id);
        chatMsg.setMessage(message);
        int ck = chatService.insertChat(chatMsg);
    }
}