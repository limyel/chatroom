package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
