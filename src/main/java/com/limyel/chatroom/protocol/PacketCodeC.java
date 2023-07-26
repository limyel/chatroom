package com.limyel.chatroom.protocol;

import com.limyel.chatroom.protocol.command.Command;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.request.MsgRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.protocol.response.MsgResponsePacket;
import com.limyel.chatroom.serialize.Serializer;
import com.limyel.chatroom.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * Packet 编解码器
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x107382;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MSG_REQUEST, MsgRequestPacket.class);
        packetTypeMap.put(Command.MSG_RESPONSE, MsgResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JsonSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    /**
     * 编码，在 handler 中创建 ByteBuf，传入当前连接相关的 ByteBuf 分配器
     * @param byteBufAllocator
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        return encode(byteBuf, packet);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 序列化 Packet
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 写入魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        // 写入协议版本
        byteBuf.writeByte(packet.getVersion());
        // 写入算法标识
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 写入命令
        byteBuf.writeByte(packet.getCommand());
        // 写入数据长度
        byteBuf.writeInt(bytes.length);
        // 写入数据
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // todo 魔数
        byteBuf.skipBytes(4);

        // todo 版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int len = byteBuf.readInt();

        // 数据包
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);

        // 获取请求类型
        Class<? extends Packet> requestType = getRequestType(command);
        // 获取序列化器
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            // 反序列化
            return serializer.deserialize(bytes, requestType);
        }

        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

}
