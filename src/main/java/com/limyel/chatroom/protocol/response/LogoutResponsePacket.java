package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponsePacket extends Packet {

    private Boolean success;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
