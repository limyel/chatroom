package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgResponsePacket extends Packet {

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MSG_RESPONSE;
    }
}
