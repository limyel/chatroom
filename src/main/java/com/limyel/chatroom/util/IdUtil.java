package com.limyel.chatroom.util;

import java.util.UUID;

public class IdUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
