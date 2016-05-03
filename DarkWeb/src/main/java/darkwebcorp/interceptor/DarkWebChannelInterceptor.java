package darkwebcorp.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.GenericMessage;

import darkwebcorp.dto.UserPreferences;
import darkwebcorp.dto.Users;
import darkwebcorp.security.AEScipher;
import darkwebcorp.util.Converter;

/**
 * 
 * file: DarkWebChannelInterceptor.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * This is custom interceptor to literally intercept the 
 * inbound and outbound
 * message/frame
 * 
 * Scope of Intercepted frames:
 * CONNECT - used to track the users who connect through the 
 *           STOMP Message broker
 * 
 * DISCONNECT - intercepted to delete the subscribers who 
 *              leave the application
 *              
 * MESSAGE - intercepted to decrypt the cipher text messages 
 *           before they are broadcasted to the subscribers
 *         - OutBound channel MESSAGE frame doesn't contain command,
 *           hence a null check is done to filter the outbound messages
 *           
 */
public class DarkWebChannelInterceptor<T> extends ChannelInterceptorAdapter {

  private Map<String, UserPreferences> users = new HashMap<>();

  private Map<String, String> subscribers = new HashMap<>();

    /**
     * 
     * preSend
     *
     * Method Information
     * This method overrides the super type method preSend and its 
     * implementation in AbstractMessageChannel
     * 
     * In the internal implementation of applyPreSend() in 
     * AbstractMessageChannel the method call preSend Overridden method 
     * in this class.
     * 
     * Hence for every call that is made to stomp broker, the super type is 
     * called, then it looks for the child classes that override the default
     * implementation, like below..
     * 
     * Message<?> resolvedMessage = interceptor.preSend(messageToUse, 
     *                           channel);
     * 
     * Now control is navigated to this class for further manipulation.
     * 
     * This happens for every channel IN and OUT bound websocket requests
     * 
     * - Uses the generic Message to fetch the message payloa.
     * 
     * - All the request that end up at this method are intercepted 
     * WebSocket messages.
     * 
     * 1. The current users and subscribers data that is maintained in the 
     * application is retrieved. 
     * 2. All the users who are registered to Dark Web application is 
     * assigned to local HASHMAP object.
     * 
     * 3. getUsers() - returns a HashMap of users.
     * 
     * 4. The preferred key of a particular user is retrieved and converted 
     * into HEX format.
     * 
     * 5. Converter utils breaks the cipher text message into blocks of 
     * equals size( 32 bit blocks).
     * 
     * 6. Padding is striped off with the help of '~' - delimiter.
     * 
     * 7. Finally
     *   -  convert each message block from byte array into TEXT
     *   - TEXT is already in HEX, which is complete cipher text message.
     *   - split the cipher message into blocks of equal size( 32 bits)
     *   - decrypt each block and create text blocks
     *   - Unnecessary bad characters(padded chars) are stripped off
     *   - generated plainText blocks are stitched together to create a 
     *     a final meaningful text message
     *   - The message text is appended back to the payload
     *   - The message is set in the generic message object and headers are
     *     added
     *   - the resolved message is sent back to the calling function.
     * 
     * Future scope:
     *   With the inclusion of MessageID, the authentication can be done 
     *    here and a decision to drop or hold the messages in queue is taken
     *  
     * Parameters:
     *   @param message
     *   @return: Generic Message<?>
     * 
     * Return value: Serialized Message object.
     */
  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
    StompCommand command = accessor.getCommand();

    users = Users.getUsers();
    subscribers = Users.getUserSubs();

    if (null != command && command.getMessageType().name().equals("CONNECT")) {
      subscribers.put(accessor.getSessionId(), accessor.getLogin());
      Users.setUserSubs(subscribers);
    }else if (null != command && 
        command.getMessageType().name().equals("DISCONNECT")) {
      users.remove(subscribers.get(accessor.getSessionId()));
      subscribers.remove(accessor.getSessionId());
      Users.setUserSubs(subscribers);
      Users.setUsers(users);
    }else if (null == command && 
        accessor.getMessageType().name().equals("MESSAGE")) {
      
      byte[] messageArr = (byte[]) message.getPayload();
      MessageHeaders headers = message.getHeaders();
      String payload = Converter.byteArrayToString(messageArr);
      
      String cText = Converter.stripMessage(payload);
      
      String userFromsubs = subscribers.get(accessor.getSessionId());
      String currentUserKey = users.get(userFromsubs).getSecureKey();
      
      String[] blocks = Converter.createBlocksOf32(cText);
      String pText = "";
      for(String block : blocks){
        pText += 
        AEScipher.aesDecrypt(block, Converter.convertToHex(currentUserKey));
      }
      pText = Converter.convertHexToText( pText );
      
      pText = Converter.stripBadChars(pText);
      
      payload = Converter.attachStrippedContent(pText,payload);
      
      messageArr = Converter.StringToByteArray(payload);
      Message<T> msg = new GenericMessage<T>((T) messageArr, headers);
      message = msg;
    }

    return message;
  }

}
