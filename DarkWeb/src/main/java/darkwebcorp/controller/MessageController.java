/**
 * 
 */
package darkwebcorp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import darkwebcorp.dto.Message;
import darkwebcorp.dto.UserPreferences;
import darkwebcorp.dto.Users;
import darkwebcorp.security.AEScipher;
import darkwebcorp.util.Converter;

/**
 * 
 * file: MessageController.java
 * author: Marist User
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: May 2, 2016
 * version: 1.0
 * 
 * This is spring controller to handle messages.
 * 
 * - MessageController will receive the messages from the chat client
 * - messages are recieved in the Message model object
 * - Create messages blocks
 * - Encrypt each block and stitch them together
 * - Set the cipher text back in Message model
 */
@Controller
public class MessageController {
  
  private static int messageId = 0;
  
  private Map<String, UserPreferences> users = new HashMap<>();
  
  /**
   * 
   * sendMessage
   *
   * Method Information
   * - Uses the model Message to fetch the message payload from the client.
   * 
   * - First annotation -  @MessageMapping("/send"), which means that
   * any client who wants to send the messages will have to send requests
   * to this end-point. The request are mapped to the method sendMessage().
   * 
   * - All the request that end up at this method are WebSocket messages.
   * 
   * - Second annotation -  @SendTo("/topic/chat") - a call back service
   * to all the subscribers in the Stomp Endpoint Registry.
   * 
   * - The current users data is maintained in the application.
   * So we retrieve all the users who are registered to Dark Web application 
   * first. This data is maintained in a static class. 
   * Users is Static class because the user preferences should be accessible 
   * across the application.
   * 
   * - getUsers() - returns a HashMap of users.
   * 
   * - The preferred key of a particular user is retrieved and converted into
   * HEX format.
   * 
   * - Converter utils breaks the message into blocks of 
   * equals size( 32 bit blocks).
   * 
   * - Padding scheme - simple padding scheme to create blocks of 32 bits
   * for short message < 16 charactes.
   * Padded characters are, '~' - delimiter, '|' - pad char
   * 
   * - Finally
   * 	- convert each message block to hex
   * 	- encrypt and create cipher blocks
   * 	- generated cipherText blocks are stitched together to create a 
   * 	  a final cipher text of message
   * 	- set the message in the model and give it message ID for tracking
   * 	  purposes.
   * 	- The messageID is actually maintained for authentication(ordering)
   * 
   * Future scope:
   * 	MessageID can be also be replaced by HashFunction generated MAC.
   *  
   * Parameters:
   *   @param message
   *   @return: Message
   * 
   * Return value: populated Message model object with cipherText.
   */
  @MessageMapping("/send")
  @SendTo("/topic/chat")
  public Message sendMessage(Message message){
    
	synchronized (message) {
	    users = Users.getUsers();
	    String key = users.get(message.getName()).getSecureKey();
	    key = Converter.convertToHex(key);
	    String[] blocks = Converter.createBlocks(message.getMessage());
	    String cText = "";
	    for(String block : blocks){
	      
	      if(block.length() < 16){
	        int padLen = 16 - block.length() - 1;
	        block = block + "~";
	        for(int j=0; j < padLen; j++){
	          block = block + "|";
	        }
	      }
	      
	      String messageHex = Converter.convertToHex(block);
	      cText += AEScipher.aes( messageHex , key );
	    }
	    
	    message.setMessage(cText);
	    message.setMessageID(messageId++);
	}
    return message;
  }
  
}
