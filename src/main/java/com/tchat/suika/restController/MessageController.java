package com.tchat.suika.restController;

import com.tchat.suika.dao.entities.Message;
import com.tchat.suika.model.*;
import com.tchat.suika.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("messages")
    public List<MessageGetDTO> getMessages() {
        List<Message> messages = messageService.getMessages();
        List<MessageGetDTO> messageGetDto = new ArrayList<>();
        for(Message entity : messages) {
            MessageGetDTO dto = MessageGetMapper.entityToDto(entity);
            messageGetDto.add(dto);
        }
        return messageGetDto;
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<MessageGetDTO> getMessage(@PathVariable Long id) {
        Optional<Message> optional = messageService.getMessage(id);
        if (optional.isPresent()) {
            Message message = optional.get();
            MessageGetDTO dto = MessageGetMapper.entityToDto(message);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("messages")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMessage(@RequestBody MessagePostDTO newMessage) {
        if (messageService.emptyField(newMessage)) {
            return ResponseEntity.badRequest().body("Un des champs n'a pas été complété");
        }

//        if(messageService.isUserExist(newMessage.getIdUser())){
//            return ResponseEntity.badRequest().body("L'utilisateur n'existe pas");
//        }

        MessagePostDTO messagePostDTO = messageService.createMessage(newMessage);
        return ResponseEntity.ok(messagePostDTO);
    }

}
