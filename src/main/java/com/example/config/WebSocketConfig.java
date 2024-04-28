package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 동적 URL 경로 처리를 위해 {movieId} 경로 변수 사용
        registry.addEndpoint("/partyroom/chat/{SomeId}").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 브로커가 /topic 경로를 기반으로 메시지를 브로드캐스트할 수 있도록 설정
        registry.enableSimpleBroker("/topic");
        // 애플리케이션 내에서 처리될 메시지의 기본 경로를 /app 으로 설정
        registry.setApplicationDestinationPrefixes("/app");
    }
}
