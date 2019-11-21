package com.first.redis.ope;

public interface MessagePublisher {
    /**
     * publish message
     * @param message
     */
    void publish(String message);
}