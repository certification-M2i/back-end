package com.tchat.suika.model;

import java.time.LocalDate;

public class GetMessagesInChannelDTO {

    private Long id;
    private String content;
    private LocalDate sendingDate;
    private String username;

    public GetMessagesInChannelDTO(Long id, String content, LocalDate sendingDate, String username) {
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

    public LocalDate getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDate sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
