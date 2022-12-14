package com.limyel.chatroom.client.console;

import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author limyel
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入登录用户名：");
        String username = scanner.nextLine();
        System.out.print("输入登录密码：");
        String password = scanner.nextLine();

        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword(password);

        channel.writeAndFlush(loginRequestPacket).addListener(future -> {
            if (!future.isSuccess()) {
                future.cause().printStackTrace();
            }
        });
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
