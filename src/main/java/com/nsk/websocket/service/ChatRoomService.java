package com.nsk.websocket.service;

import com.nsk.websocket.chatroom.ChatRoom;
import com.nsk.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(
            String senderId,
            String receiverId,
            boolean createdNewRoomIfNotExist
    ){
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId,receiverId)
                .map(ChatRoom::getId)
                .or(
                        ()->{
                            if(createdNewRoomIfNotExist){
                var chatId = createChatId(senderId,receiverId);
                return Optional.of(chatId);
                            }
                            return Optional.empty();
                        });

    }

    private String createChatId(String senderId, String receiverId) {

    var chatId =  String.format("%s_%s", senderId, receiverId);
    ChatRoom senderRecipient = new ChatRoom().builder()
            .chatId(chatId)
            .senderId(senderId)
            .receiverId(receiverId)
            .build();
    ChatRoom recipientSender = new ChatRoom().builder()
            .chatId(chatId)
            .senderId(receiverId)
            .receiverId(senderId)
            .build();
    chatRoomRepository.save(senderRecipient);
    chatRoomRepository.save(recipientSender);
    return chatId;
    }
}
