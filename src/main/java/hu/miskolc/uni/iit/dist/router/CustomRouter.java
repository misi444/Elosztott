package hu.miskolc.uni.iit.dist.router;

import hu.miskolc.uni.iit.dist.integration.SystemMessage;
import org.springframework.integration.annotation.Router;

public class CustomRouter {
    @Router
    public String routeDecision(SystemMessage message) {
        switch (message.getType())
        {
            case 1:
                return "channel7";
            case 2:
                return "channel11";
            default:
                return "channel3";
        }
    }
}