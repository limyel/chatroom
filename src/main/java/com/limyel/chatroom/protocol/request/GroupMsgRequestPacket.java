package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMsgRequestPacket extends Packet {

    private String groupId;

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_REQUEST;
    }
}
