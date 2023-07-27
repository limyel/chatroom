package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return null;
    }
}
