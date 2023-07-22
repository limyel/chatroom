package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgRequestPacket extends Packet {

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MSG_REQUEST;
    }
}
