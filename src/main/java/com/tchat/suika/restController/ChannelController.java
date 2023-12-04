package com.tchat.suika.restController;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.model.dtos.ChannelCreationDTO;
import com.tchat.suika.model.dtos.GetChannelDTO;
import com.tchat.suika.model.dtos.GetMessagesInChannelDTO;
import com.tchat.suika.model.dtos.UpdateChannelDTO;
import com.tchat.suika.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelCreationDTO> createChannel(@RequestBody ChannelCreationDTO channelCreationDto) {
        ChannelCreationDTO newChannel = channelService.saveChannel(channelCreationDto);
        return new ResponseEntity<>(newChannel, HttpStatus.CREATED);
    }

    @GetMapping("/{channelId}/messages")
    public ResponseEntity<List<GetMessagesInChannelDTO>> getMessagesByChannelId(@PathVariable Long channelId) {
        List<GetMessagesInChannelDTO> messages = channelService.getMessagesByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        return channelService.getChannelById(id)
                .map(channel -> new ResponseEntity<>(channel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping
    public ResponseEntity<List<GetChannelDTO>> getAllChannelsOfUser(@RequestParam String username) {
        List<GetChannelDTO> channels = channelService.getAllChannels(username);
        return ResponseEntity.ok(channels);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> renameChannel(@PathVariable Long id, @RequestBody UpdateChannelDTO channelUpdateDto) {
        Channel updatedChannel = channelService.renameChannel(id, channelUpdateDto.getNewName());
        return ResponseEntity.ok(updatedChannel.getId());
    }

    @PostMapping("/{channelId}/users/{userId}")
    public ResponseEntity<String> assignUserInChannel(@PathVariable Long channelId, @PathVariable Long userId){
        String assignUser = channelService.assignUserToChannel(channelId, userId);
        return ResponseEntity.ok(assignUser);
    }

    @DeleteMapping("/{channelId}/users/{userId}")
    public ResponseEntity<String> removeUserFromChannel(@PathVariable Long channelId, @PathVariable Long userId) {
        String response = channelService.removeUserFromChannel(channelId, userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteChannel(@PathVariable Long id) {
        return ResponseEntity.ok(channelService.deleteChannel(id));
    }
}
