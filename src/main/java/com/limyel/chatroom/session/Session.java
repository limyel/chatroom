package com.limyel.chatroom.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author limyel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private Long userId;

    private String username;

    @Override
    public String toString() {
        return userId + ":" + username;
    }

}
