package com.limyel.chatroom.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    /**
     * 执行命令
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);

}
