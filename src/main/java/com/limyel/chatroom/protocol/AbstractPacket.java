package com.limyel.chatroom.protocol;

import lombok.Data;

/**
 * @author limyel
 */
@Data
public abstract class AbstractPacket {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();

}
