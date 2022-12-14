package com.limyel.chatroom.client;

import com.limyel.chatroom.config.ChatroomConfig;
import com.limyel.chatroom.protocol.PacketCodec;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ClientStart {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ChatroomConfig.class);

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        Client client = applicationContext.getBean(Client.class);
        client.start();
    }
}
