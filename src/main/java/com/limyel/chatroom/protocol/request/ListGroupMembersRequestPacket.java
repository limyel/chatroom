package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
