package com.tchat.suika.model.dtos;

import java.time.LocalDate;

public class ChannelCreationDTO {

    private String name;
    private String creatorUsername;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }
}
