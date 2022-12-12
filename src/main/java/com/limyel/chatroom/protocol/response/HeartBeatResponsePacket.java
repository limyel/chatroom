package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;

public class HeartBeatResponsePacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandConstant.HEARTBEAT_RESPONSE;
    }
}
