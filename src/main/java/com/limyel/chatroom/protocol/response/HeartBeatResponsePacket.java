package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatResponsePacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandConstant.HEARTBEAT_RESPONSE;
    }
}
