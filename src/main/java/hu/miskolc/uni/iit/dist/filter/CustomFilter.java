package hu.miskolc.uni.iit.dist.filter;

import hu.miskolc.uni.iit.dist.integration.SystemMessage;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

public class CustomFilter implements MessageSelector {
    @Override
    public boolean accept(Message<?> message) {
        if (message.getPayload() instanceof SystemMessage) {
            SystemMessage systemMessage = (SystemMessage) message.getPayload();
            if (systemMessage.getMessage() != null) {
                if (systemMessage.getMessage().contains("77")) {
                    return true;
                }
            }
        }

        return false;
    }
}