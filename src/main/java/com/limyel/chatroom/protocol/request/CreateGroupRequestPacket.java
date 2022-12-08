package com.limyel.chatroom.protocol.request;

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
public class CreateGroupRequestPacket extends AbstractPacket {

    private List<Long> userIdList;

    @Override
    public Byte getCommand() {
        return CommandConstant.CREATE_GROUP_REQUEST;
    }
}
