package com.limyel.chatroom.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author limyel
 */
@Data
public abstract class AbstractPacket {

    /**
     * 协议版本
     */
//    @JsonIgnore
    protected Byte version = 1;

    /**
     * 指令
     * @return
     */
    @JsonIgnore
    public abstract Byte getCommand();

}
