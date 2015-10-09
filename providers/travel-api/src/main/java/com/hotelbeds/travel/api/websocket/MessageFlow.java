package com.hotelbeds.travel.api.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MessageFlow {

    private String topic = SocketConfig.WEBSOCKET_SERVICE + "/flow";

    @Autowired
    private SimpMessagingTemplate template;

    public void publish(String msg) {
        try {
            template.convertAndSend(topic, msg);
        } catch (Exception e) {
            StringBuffer exception = new StringBuffer("Error publishing at topic: ");
            exception.append(topic);
            exception.append(" exception: ");
            exception.append(e.getMessage());
        }
    }
}
