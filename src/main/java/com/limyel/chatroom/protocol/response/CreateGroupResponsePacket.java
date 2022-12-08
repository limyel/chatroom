package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupResponsePacket extends AbstractPacket {

    private boolean success;

    private Long groupId;

    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return CommandConstant.CREATE_GROUP_RESPONSE;
    }
}
