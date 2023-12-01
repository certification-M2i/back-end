package com.tchat.suika.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name="date_creation")
    @CreationTimestamp
    private LocalDateTime dateCreation;

    @ManyToMany
    @JoinTable(name="channel_users",
            joinColumns = @JoinColumn(name="channel_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "channel", cascade=CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    private boolean isDefault;

    public Channel() {
    }

    public Channel(String name, LocalDateTime dateCreation, List<User> users, List<Message> messages, boolean isDefault) {
        this.name = name;
        this.dateCreation = dateCreation;
        this.users = users;
        this.messages = messages;
        this.isDefault = isDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreation=" + dateCreation +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
