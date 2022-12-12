package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;

public class HeartBeatRequestPacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandConstant.HEARTBEAT_REQUEST;
    }
}
