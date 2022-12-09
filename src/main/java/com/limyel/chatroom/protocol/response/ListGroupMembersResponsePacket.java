package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersResponsePacket extends AbstractPacket {

    private Long groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return CommandConstant.LIST_GROUP_MEMBERS_REQUEST;
    }
}
