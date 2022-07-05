package com.lincoln.wesocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * description:
 *
 * @author linye
 * @date 2022年07月01日 11:39 PM
 */
public class GetHttpSessionConfig extends Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        // 获取session
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        if (null != httpSession) {
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }

    }
}
