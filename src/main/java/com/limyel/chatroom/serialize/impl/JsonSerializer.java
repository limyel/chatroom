package com.limyel.chatroom.serialize.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limyel.chatroom.serialize.Serializer;
import com.limyel.chatroom.serialize.SerializerAlgorithm;

import java.io.IOException;

public class JsonSerializer implements Serializer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) {
        try {
            return mapper.readValue(bytes, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
