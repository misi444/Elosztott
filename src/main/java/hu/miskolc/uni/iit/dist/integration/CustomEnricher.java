package hu.miskolc.uni.iit.dist.integration;

import org.springframework.messaging.Message;

import java.util.Date;

public class CustomEnricher {
    public SystemMessage enrich(Message<SystemMessage> message) {
        SystemMessage result = message.getPayload();
        result.setDate(new Date());
        return result;
    }
}