package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponsePacket extends Packet {

    private String uuid;

    private String username;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
