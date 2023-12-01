package com.tchat.suika.config;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultChannelInitializer implements CommandLineRunner {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public void run(String... args) throws Exception {
        if (channelRepository.findByName("Général").isEmpty()) {
            Channel defaultChannel = new Channel();
            defaultChannel.setName("Général");
            defaultChannel.setDefault(true);
            channelRepository.save(defaultChannel);
        }
    }

}
