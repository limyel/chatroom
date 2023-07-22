package com.limyel.chatroom.serialize;

import com.limyel.chatroom.serialize.impl.JsonSerializer;

public interface Serializer {

    // 默认实现
    Serializer DEFAULT = new JsonSerializer();

    /**
     * 获取序列化算法标识
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @return
     * @param <T>
     */
    <T> T deserialize(byte[] bytes, Class<T> type);

}
