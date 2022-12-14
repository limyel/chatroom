package com.limyel.chatroom.server;

import com.limyel.chatroom.config.ChatroomConfig;
import com.limyel.chatroom.serializer.impl.JSONSerializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ServerStart {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ChatroomConfig.class);

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(applicationContext.getBean(JSONSerializer.class));

        Server server = applicationContext.getBean(Server.class);
        server.start();
    }

}
