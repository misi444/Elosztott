package hu.miskolc.uni.iit.dist.gateway;

import hu.miskolc.uni.iit.dist.integration.SystemMessage;

public interface Gateway {
    void send(SystemMessage message);
}