package com.tchat.suika.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetMessagesInChannelDTO {

    private Long id;
    private String content;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime sendingDate;
    private String username;

    public GetMessagesInChannelDTO(Long id, String content, LocalDateTime sendingDate, String username) {
        this.id = id;
        this.content = content;
        this.sendingDate = sendingDate;
        this.username = username;
    }

    public GetMessagesInChannelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDateTime sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
