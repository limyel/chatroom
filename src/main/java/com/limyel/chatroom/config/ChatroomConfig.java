package com.limyel.chatroom.config;

import com.limyel.chatroom.constant.SerializerTypeConstant;
import com.limyel.chatroom.serializer.Serializer;
import com.limyel.chatroom.serializer.impl.JSONSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.limyel.chatroom")
@Configuration
@PropertySource("classpath:application.properties")
public class ChatroomConfig {

    @Value("${serializerType:JSON}")
    private String serializerType;

    // todo 多个 serializer？
    @Bean
    public Serializer serializer() {
        Serializer serializer = null;
        if (serializerType.equals(SerializerTypeConstant.JSON)) {
            serializer = new JSONSerializer();
        }
        return serializer;
    }

}
