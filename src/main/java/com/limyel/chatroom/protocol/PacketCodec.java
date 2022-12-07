package com.limyel.chatroom.protocol;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.serializer.Serializer;
import com.limyel.chatroom.serializer.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装二进制数据包
 * @author limyel
 */
public class PacketCodec {

    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    // todo 单例模式？
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends AbstractPacket>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;

    public PacketCodec() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CommandConstant.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(CommandConstant.LOGIN_RESPONSE, LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(AbstractPacket packet) {
        // 创建 ByteBuf 对象
        ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 魔数
        buf.writeInt(MAGIC_NUMBER);
        // 版本
        buf.writeByte(packet.getVersion());
        // 序列化算法
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 指令
        buf.writeByte(packet.getCommand());
        // 数据包长度
        buf.writeInt(bytes.length);
        // 数据包
        buf.writeBytes(bytes);

        return buf;
    }

    public AbstractPacket decode(ByteBuf buf) {
        // 跳过魔数
        buf.skipBytes(4);

        // 跳过版本
        buf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = buf.readByte();

        // 指令
        byte command = buf.readByte();

        // 数据包长度
        int length = buf.readInt();

        byte[] bytes = new byte[length];
        buf.readBytes(bytes);

        Class<? extends AbstractPacket> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends AbstractPacket> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
