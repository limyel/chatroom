package com.limyel.chatroom.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author limyel
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
