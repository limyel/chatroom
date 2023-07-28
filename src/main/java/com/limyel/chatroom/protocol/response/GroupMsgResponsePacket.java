package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMsgResponsePacket extends Packet {

    private String fromGroupId;

    private String fromUsername;

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_RESPONSE;
    }
}
