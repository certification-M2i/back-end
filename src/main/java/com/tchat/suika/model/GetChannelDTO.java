package com.tchat.suika.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class GetChannelDTO {
    private Long id;
    private String name;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dateCreation;

    public GetChannelDTO() {
    }

    public GetChannelDTO(Long id, String name, LocalDateTime dateCreation) {
        this.id = id;
        this.name = name;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
