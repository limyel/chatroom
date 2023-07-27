package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
