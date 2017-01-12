package hu.miskolc.uni.iit.dist.integration;

public class Shouter {
    public SystemMessage shout1(SystemMessage message) {
        message.setMessage(message.toString().toUpperCase().concat(" !!!"));
        return message;
    }

    public String shout2(SystemMessage message) {
        return message.toString().toUpperCase().concat(" !!!");
    }
}