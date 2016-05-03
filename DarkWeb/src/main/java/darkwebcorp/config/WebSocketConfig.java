package darkwebcorp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import darkwebcorp.interceptor.DarkWebChannelInterceptor;
/**
 * 
 * file: WebSocketConfig.java
 * author: Marist User
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: May 2, 2016
 * version: 1.0
 * 
 * A convenient class for WebSocket MessageBroker Configuration. 
 * Implementations provide empty methods for,
 * 1) configureMessageBroker 
 *       - set subscriber prefix
 *       - set application destination prefix
 * 
 * 2) registerStompEndpoints
 *       - register the end points for subscriptions
 * 
 * 3) configureClientInboundChannel
 *       - register in bound channel interceptor to
 *         DarkWebChannelInterceptor.java which is 
 *         custom interceptor to process all incoming requests
 * 
 * 4) configureClientOutboundChannel
 *       - register out bound channel interceptor to
 *         DarkWebChannelInterceptor.java which is 
 *         custom interceptor to process all outgoing requests to clients
 */
@Configuration("darkwebcorp.config")
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  /**
   * - set subscriber prefix
   * - set application destination prefix
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  /**
   * - register the end points for subscriptions
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/send").withSockJS();
    registry.addEndpoint("/receive").withSockJS();
  }
  
  /**
   *   Register in bound channel interceptor to
   *   DarkWebChannelInterceptor.java which is 
   *   custom interceptor to process all incoming requests
   */
  @Override
  public void configureClientInboundChannel(ChannelRegistration registration){
      registration.setInterceptors(new DarkWebChannelInterceptor());
  }
  
  /**
   * register out bound channel interceptor to
   * DarkWebChannelInterceptor.java which is 
   * custom interceptor to process all outgoing requests to clients
   */
  @Override
  public void configureClientOutboundChannel(ChannelRegistration registration){
     registration.setInterceptors(new DarkWebChannelInterceptor());
  }
  
}